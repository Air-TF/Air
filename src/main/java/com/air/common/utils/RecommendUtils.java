package com.air.common.utils;

import com.air.bean.History;
import com.air.bean.SimilarityDTO;

import java.util.*;
import java.util.stream.Collectors;

public class RecommendUtils {
    private RecommendAlgorithm algorithm = new RecommendAlgorithm();

    private static class SingletonHolder {
        private static final RecommendUtils RECOMMEND_UTILS = new RecommendUtils();
    }

    public static RecommendUtils getUtils() {
        return SingletonHolder.RECOMMEND_UTILS;
    }

    private class RecommendAlgorithm {

        /**
         * 欧式距离
         *
         * @param map1
         * @param map2
         * @return
         */
        private double euclideanDistance(Map<?, Double> map1, Map<?, Double> map2) {
            Set set = new HashSet(map1.keySet());
            set.addAll(map2.keySet());
            Object[] array = set.toArray();
            double sum = 0;
            for (int i = 0; i < array.length; i++)
                sum += Math.pow(getValue(map1, array[i]) - getValue(map2, array[i]), 2);

            return Math.sqrt(sum);
        }

        /**
         * 余弦距离
         *
         * @param map1
         * @param map2
         * @return
         */
        public double cosineDistance(Map<?, Double> map1, Map<?, Double> map2) {
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
     * @param historyList 历史纪录
     * @param id          用户id
     * @param num         推荐条数
     * @return 推荐产品id列表
     */
    public List<Long> UserBased(List<History> historyList, String id, Integer num) {
        //数据转化 Map<UserId,Map<ItemId,score>>
        Map<String, Map<Long, Double>> userMap = new HashMap<>();
        for (History history : historyList) {
            String userId = history.getUserId();
            if (userMap.get(userId) == null) {
                HashMap<Long, Double> hashMap = new HashMap<>();
                userMap.put(userId, hashMap);
            }
            userMap.get(userId).put(history.getItemId(), getUserBaseScore(history));
        }

        //计算用户相似度
        List<SimilarityDTO<String>> userSimilarity = getSimilarityList(userMap, id, 20);

        //获取推荐物品列表
        List<Long> itemList = getRecommendItem(userMap, userSimilarity);

        //排除已经收藏物品
        ArrayList<Map.Entry<Long, Double>> collectedList = new ArrayList<>(userMap.get(id).entrySet());
        for (int i = 0; i < itemList.size(); i++) {
            for (Map.Entry<Long, Double> longDoubleEntry : collectedList) {
                if (longDoubleEntry.getKey() > 5) {
                    if (itemList.get(i) == longDoubleEntry.getKey().longValue()) {
                        itemList.remove(i);
                        break;
                    }
                }
            }
        }

//        //获取推荐物品列表前num条数据
//        List<Long> list = new ArrayList<>();
//        num = itemList.size() > num ? num : itemList.size();
//        for (int i = 0; i < num; i++) {
//            list.add(itemList.get(i));
//        }
//        return itemList.subList(0,num);

        //返回推荐物品列表
        return itemList.stream().limit(num).collect(Collectors.toList());
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
        for (History history : historyList) {
            Long itemId = history.getItemId();
            if (itemMap.get(itemId) == null) {
                HashMap<String, Double> hashMap = new HashMap<>();
                itemMap.put(itemId, hashMap);
            }
            itemMap.get(itemId).put(history.getUserId(), getItemBaseScore(history));
        }

        //计算相似度
        List<SimilarityDTO<Long>> itemSimilarity = getSimilarityList(itemMap, id, num);

        //返回推荐物品id列表
        //        num = itemSimilarity.size() > num ? num : itemSimilarity.size();
//        for (int i = 0; i < num; i++) {
//            list.add(itemSimilarity.get(i).getT2());
//        }
//        for (SimilarityDTO<Long> similarityDTO : itemSimilarity) {
//            list.add(similarityDTO.getT2());
//        }

        return itemSimilarity.stream().map(SimilarityDTO::getT2).collect(Collectors.toList());
    }

    /**
     * 计算相似度
     *
     * @param itemMap 评分矩阵
     * @param id
     * @param <T>     相关类型
     * @param <H>     被相关类型
     * @return
     */
    private <T, H> List<SimilarityDTO<T>> getSimilarityList(Map<T, Map<H, Double>> itemMap, T id, int k) {
        //计算相似度
        T[] KeyArr = (T[]) itemMap.keySet().toArray();
        ArrayList<SimilarityDTO<T>> itemSimilarity = new ArrayList<>();
        for (T t : KeyArr) {
            if (t.equals(id)) continue;
            itemSimilarity.add(new SimilarityDTO<>(id, t, algorithm.euclideanDistance(itemMap.get(id), itemMap.get(t))));
        }

        //相似度升序排序
        itemSimilarity.sort(Comparator.comparingDouble(SimilarityDTO::getSimilarity));

//        k = itemSimilarity.size() > k ? k : itemSimilarity.size();
//        return itemSimilarity.subList(0, k);
        return itemSimilarity.stream().limit(k).collect(Collectors.toList());
    }

    /**
     * 计算UserBased推荐物品列表
     *
     * @param historyMap 历史纪录
     * @param similarity 相似度List
     * @return
     */
    private List<Long> getRecommendItem(Map<String, Map<Long, Double>> historyMap, List<SimilarityDTO<String>> similarity) {
        //计算推荐度  Map<itemId,推荐度>
        HashMap<Long, Double> recommendMap = new HashMap<>();
        double sumSimilarity = similarity.stream().mapToDouble(SimilarityDTO::getSimilarity).sum();
//        for (SimilarityDTO similarityDTO : similarity) sumSimilarity += similarityDTO.getSimilarity();
        for (SimilarityDTO<String> stringSimilarityDTO : similarity) {
            //Map<itemId,评分>
            Map<Long, Double> itemMap = historyMap.get(stringSimilarityDTO.getT2());
            Long[] itemKey = itemMap.keySet().toArray(new Long[0]);
            for (Long itemId : itemKey) {
                double rate = getValue(recommendMap, itemId) + itemMap.get(itemId) * (1 - stringSimilarityDTO.getSimilarity() / sumSimilarity);
                recommendMap.put(itemId, rate);
            }
        }

        //推荐列表按推荐度逆序排序
        ArrayList<Map.Entry<Long, Double>> recommendList = new ArrayList<>(recommendMap.entrySet());
        recommendList.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        //返回推荐物品Id
//        List<Long> list = new ArrayList<>();
//        for (Map.Entry<Long, Double> longDoubleEntry : recommendList) {
//            list.add(longDoubleEntry.getKey());
//        }

        return recommendList.stream().map(Map.Entry::getKey).collect(Collectors.toList());
    }

    /**
     * 获取评分
     *
     * @param history
     * @return
     */
    private double getUserBaseScore(History history) {
        double score = 0.0;
        if (history.isFavorite()) score += 2.0;
        if (history.isStar()) score += 3.0;
        score += history.getTimes() * 0.3;
        return score > 10 ? 10 : score;
    }

    /**
     * 获取评分
     *
     * @param history
     * @return
     */
    private Double getItemBaseScore(History history) {
        double score = 0.0;
        if (history.isFavorite() || history.isStar()) score = 1.0;
        return score;
    }

    /**
     * 安全获取Map值
     *
     * @param map
     * @param key
     * @return
     */
    private double getValue(Map<?, Double> map, Object key) {
        Double value = map.get(key);
        if (value == null) return 0;
        return value;
    }
}