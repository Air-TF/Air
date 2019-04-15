package com.air.common.utils;

import com.air.bean.History;
import com.air.bean.SimilarityDTO;

import java.util.*;

public class RecommendUtils {
    RecommendAlgorithm algorithm = new RecommendAlgorithm();

    private static class SingletonHolder {
        private static final RecommendUtils RECOMMEND_UTILS = new RecommendUtils();
    }

    public static RecommendUtils getUtils() {
        return SingletonHolder.RECOMMEND_UTILS;
    }

    private class RecommendAlgorithm {
        /**
         * 杰卡德相似性度量算法
         *
         * @param map1
         * @param map2
         * @return
         */
        private double jacCardSimilarity(Map<?, Double> map1, Map<?, Double> map2) {
            Set set = new HashSet(map1.keySet());
            set.addAll(map2.keySet());
            Object[] array = set.toArray();
            double sum = 0;
            for (int i = 0; i < array.length; i++)
                sum += Math.pow(getValue(map1, array[i]) - getValue(map2, array[i]), 2);

            return Math.sqrt(sum);
        }

        /**
         * 余弦相似度算法
         *
         * @return
         */
        public double cosineSimilarity(Map<?, Double> map1, Map<?, Double> map2) {
            Set set = new HashSet(map1.keySet());
            set.addAll(map2.keySet());
            Object[] array = set.toArray();
            double map1Value = 0, map2Value = 0, sum = 0;

            for (int i = 0; i < array.length; i++) {
                map1Value += getValue(map1, array[i]);
                map2Value += getValue(map2, array[i]);
                sum += getValue(map1, array[i]) * getValue(map2, array[i]);
            }

            return (Math.sqrt(map1Value) * Math.sqrt(map2Value)) == 0 ? 0 : sum / (Math.sqrt(map1Value) * Math.sqrt(map2Value));
        }
    }

    /**
     * 用户相关度推荐
     *
     * @param historyList
     * @param id
     * @return
     */
    public List<Long> UserBased(List<History> historyList, String id, Integer num) {
        //数据转化
        Map<String, Map<Long, Double>> userMap = new HashMap<>();
        for (int i = 0; i < historyList.size(); i++) {
            String userId = historyList.get(i).getUserId();
            if (userMap.get(userId) == null) {
                HashMap<Long, Double> hashMap = new HashMap<>();
                userMap.put(userId, hashMap);
            }
            userMap.get(userId).put(historyList.get(i).getItemId(), getUserBaseScore(historyList.get(i)));
        }

        //计算用户相似度
        List<SimilarityDTO<String>> userSimilarity = getSimilarityList(userMap, id);

//        //计算相似度
//        String[] keyStrings = userMap.keySet().toArray(new String[0]);
//        ArrayList<SimilarityDTO<String>> userSimilarity = new ArrayList<>();
//        for (int i = 0; i < keyStrings.length; i++) {
//            if (keyStrings[i].equals(id)) continue;
//            userSimilarity.add(new SimilarityDTO<>(id, keyStrings[i], algorithm.cosineSimilarity(userMap.get(id), userMap.get(keyStrings[i]))));
//        }
//
//        //相似度排序
//        Collections.sort(userSimilarity, new Comparator<SimilarityDTO<String>>() {
//            @Override
//            public int compare(SimilarityDTO<String> o1, SimilarityDTO<String> o2) {
//                if (o1.getSimilarity() > o2.getSimilarity())
//                    return -1;
//                else if (o1.getSimilarity() < o2.getSimilarity())
//                    return 1;
//                else return 0;
//            }
//        });

        //返回推荐物品
        return calcRecommendItem(userMap, userSimilarity, 11, num);
    }

    /**
     * 物品相关度推荐
     *
     * @param historyList
     * @param id
     * @param num
     * @return
     */
    public List<Long> ItemBased(List<History> historyList, Long id, Integer num) {
        //数据转化
        Map<Long, Map<String, Double>> itemMap = new HashMap<>();
        for (int i = 0; i < historyList.size(); i++) {
            Long itemId = historyList.get(i).getItemId();
            if (itemMap.get(itemId) == null) {
                HashMap<String, Double> hashMap = new HashMap<>();
                itemMap.put(itemId, hashMap);
            }
            itemMap.get(itemId).put(historyList.get(i).getUserId(), getItemBaseScore(historyList.get(i)));
        }

        //计算相似度
        List<SimilarityDTO<Long>> itemSimilarity = getSimilarityList(itemMap, id);

        //返回指定数目推荐物品
        List<Long> list = new ArrayList<>();
        num = itemSimilarity.size() > num ? num : itemSimilarity.size();
        for (int i = 0; i < num; i++) {
            list.add(itemSimilarity.get(i).getT2());
        }

        return list;
    }

    private <T, H> List<SimilarityDTO<T>> getSimilarityList(Map<T, Map<H, Double>> itemMap, T id) {
        //计算相似度
        T[] KeyArr = (T[]) itemMap.keySet().toArray();
        ArrayList<SimilarityDTO<T>> itemSimilarity = new ArrayList<>();
        for (T t : KeyArr) {
            if (t.equals(id)) continue;
            itemSimilarity.add(new SimilarityDTO<>(id, t, algorithm.jacCardSimilarity(itemMap.get(id), itemMap.get(t))));
        }

        //相似度排序
        Collections.sort(itemSimilarity, new Comparator<SimilarityDTO>() {
            @Override
            public int compare(SimilarityDTO o1, SimilarityDTO o2) {
                if (o1.getSimilarity() > o2.getSimilarity())
                    return -1;
                else if (o1.getSimilarity() < o2.getSimilarity())
                    return 1;
                else return 0;
            }
        });

        return itemSimilarity;
    }

    /**
     * 计算推荐对象
     *
     * @param historyMap 历史纪录
     * @param similarity 相似度List
     * @param top        按前top对象相似推荐
     * @param num        推荐数量
     * @return
     */
    private List<Long> calcRecommendItem(Map<String, Map<Long, Double>> historyMap, List<? extends SimilarityDTO> similarity, int top, int num) {
        //计算推荐度  Map<itemId,推荐度>
        HashMap<Long, Double> recommendMap = new HashMap<>();
        top = top > similarity.size() ? similarity.size() : top;
        for (int i = 0; i < top; i++) {
            Map<Long, Double> itemMap = historyMap.get(similarity.get(i).getT2());
            Long[] itemKey = itemMap.keySet().toArray(new Long[0]);
            for (Long aLong : itemKey) {
                double rate = getValue(recommendMap, aLong) + itemMap.get(aLong) * similarity.get(i).getSimilarity();
                recommendMap.put(aLong, rate);
            }
        }

        //推荐列表按推荐度逆序排序
        ArrayList<Map.Entry<Long, Double>> recommendList = new ArrayList<>(recommendMap.entrySet());
        Collections.sort(recommendList, new Comparator<Map.Entry<Long, Double>>() {
            @Override
            public int compare(Map.Entry<Long, Double> o1, Map.Entry<Long, Double> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });


        //排除已经收藏物品
        ArrayList<Map.Entry<Long, Double>> collectedList = new ArrayList<>(historyMap.get(similarity.get(0).getT1()).entrySet());
        for (int i = 0; i < recommendList.size(); i++) {
            for (Map.Entry<Long, Double> longDoubleEntry : collectedList) {
                if (recommendList.get(i).getKey().longValue() == longDoubleEntry.getKey().longValue()) {
                    recommendList.remove(i);
                    break;
                }
            }
        }

        //返回指定数目推荐物品
        List<Long> list = new ArrayList<>();
        num = recommendList.size() > num ? num : recommendList.size();
        for (int i = 0; i < num; i++) {
            list.add(recommendList.get(i).getKey());
        }

        return list;
    }

    private double getUserBaseScore(History history) {
        double score = 0.0;
        if (history.isFavorite()) score += 2.0;
        if (history.isStar()) score += 3.0;
        score += history.getTimes() * 0.3;
        return score > 10 ? 10 : score;
    }

    private Double getItemBaseScore(History history) {
        double score = 0.0;
        if (history.isFavorite() || history.isStar()) score = 1.0;
        return score;
    }

    private double getValue(Map<?, Double> map, Object key) {
        Double value = map.get(key);
        if (value == null) return 0;
        return value;
    }
}