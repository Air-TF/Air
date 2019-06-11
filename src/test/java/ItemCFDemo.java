
import java.util.*;

public class ItemCFDemo {

    //系统用户
    private static String[] users={"小明","小花","小美","小张","小李"};
    //和这些用户相关的电影
    private static String[] movies={"电影1","电影2","电影3","电影4","电影5","电影6","电影7"};
    //用户点评电影情况
    private static Integer[][] allUserMovieCommentList={
            {1,1,1,0,1,0,0},
            {0,1,1,0,0,1,0},
            {1,0,1,1,1,1,1},
            {1,1,1,1,1,0,0},
            {1,1,0,1,0,1,1}
    };
    //用户点评电影情况，行转列
    private static Integer[][] allMovieCommentList=new Integer[allUserMovieCommentList[0].length][allUserMovieCommentList.length];
    //电影相似度
    private static HashMap<String,Double> movieABSimilaritys=null;
    //待推荐电影相似度列表
    private static HashMap<Integer,Object> movieSimilaritys=null;
    //用户所在的位置
    private static Integer targetUserIndex=null;
    //目标用户点评过的电影
    private static List<Integer> targetUserCommentedMovies=null;
    //推荐电影
    private static  List<Map.Entry<Integer, Object>> recommlist=null;

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String user=scanner.nextLine();
        while (user!=null && !"exit".equals(user)){
            targetUserIndex=getUserIndex(user);
            if(targetUserIndex==null){
                System.out.println("没有搜索到此用户，请重新输入：");
            }else{
                //转换目标用户电影点评列表
                targetUserCommentedMovies=Arrays.asList(allUserMovieCommentList[targetUserIndex]);
                //计算电影相似度
                calcAllMovieSimilaritys();
                //获取全部待推荐电影
                calcRecommendMovie();
                //输出推荐电影
                System.out.print("推荐电影列表：");
                for (Map.Entry<Integer, Object> item:recommlist){
                    System.out.print(movies[item.getKey()]+"  ");
                }
                System.out.println();
            }

            user=scanner.nextLine();
        }

    }

    /**
     * 获取全部推荐电影
     */
    private static void calcRecommendMovie(){
        movieSimilaritys=new HashMap<>();
        for (int i=0;i<targetUserCommentedMovies.size()-1;i++){
            for (int j=i+1;j<targetUserCommentedMovies.size();j++){
                Object similarity=null;
                if(targetUserCommentedMovies.get(i)==1 &&  targetUserCommentedMovies.get(j)==0 && ( movieABSimilaritys.get(i+""+j)!=null || movieABSimilaritys.get(j+""+i)!=null)){
                    similarity=movieABSimilaritys.get(i+""+j)!=null?movieABSimilaritys.get(i+""+j):movieABSimilaritys.get(j+""+i);
                    movieSimilaritys.put(j,similarity);
                }else if(targetUserCommentedMovies.get(i)==0 &&  targetUserCommentedMovies.get(j)==1 && (movieABSimilaritys.get(i+""+j)!=null || movieABSimilaritys.get(j+""+i)!=null)){
                    similarity=movieABSimilaritys.get(i+""+j)!=null?movieABSimilaritys.get(i+""+j):movieABSimilaritys.get(j+""+i);
                    movieSimilaritys.put(i,similarity);
                }
            }
        }

        recommlist = new ArrayList<>(movieSimilaritys.entrySet());
        Collections.sort(recommlist, new Comparator<Map.Entry<Integer, Object>>() {
            @Override
            public int compare(Map.Entry<Integer, Object> o1, Map.Entry<Integer, Object> o2) {
                return o1.getValue().toString().compareTo(o2.getValue().toString());
            }
        });

        System.out.println("待推荐相似度电影列表："+recommlist);
    }

    /**
     * 计算全部物品间的相似度
     */
    private static void calcAllMovieSimilaritys(){
        converRow2Col();
        movieABSimilaritys=new HashMap<>();
        for (int i=0;i<allMovieCommentList.length-1;i++){
            for (int j=i+1;j<allMovieCommentList.length;j++){
                movieABSimilaritys.put(i+""+j,calcTwoMovieSimilarity(allMovieCommentList[i],allMovieCommentList[j]));
            }
        }

        System.out.println("电影相似度："+movieABSimilaritys);
    }

    /**
     * 根据电影全部点评数据，计算两个电影相似度
     * @param movie1Stars
     * @param movie2Starts
     * @return
     */
    private static double calcTwoMovieSimilarity(Integer[] movie1Stars,Integer[] movie2Starts){
        float sum=0;
        for(int i=0;i<movie1Stars.length;i++){
            sum+=Math.pow(movie1Stars[i]-movie2Starts[i],2);
        }
        return Math.sqrt(sum);
    }

    /**
     * 数组行转列
     */
    private static void converRow2Col(){
        for (int i=0;i<allUserMovieCommentList[0].length;i++){
            for(int j=0;j<allUserMovieCommentList.length;j++){
                allMovieCommentList[i][j]=allUserMovieCommentList[j][i];
            }
        }
        System.out.println("电影点评转行列："+Arrays.deepToString(allMovieCommentList));
    }

    /**
     * 查找用户所在的位置
     * @param user
     * @return
     */
    private static Integer getUserIndex(String user){
        if(user==null || "".contains(user)){
            return null;
        }

        for(int i=0;i<users.length;i++){
            if(user.equals(users[i])){
                return i;
            }
        }

        return null;
    }
}