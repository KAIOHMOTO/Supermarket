import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Root {


    public static void main(String args[]){

        List<Point> points =new ArrayList<>();



        Path path1 = Paths.get("C:\\Users\\k-omoto\\Documents\\ontology\\route1.csv");
        Path path2 = Paths.get("C:\\Users\\k-omoto\\Documents\\ontology\\root.csv");


        try {
            points.add(new Point(0,0));
            List<String> line1 = Files.readAllLines(path1);
            List<String> line2 = Files.readAllLines(path2);

            for(int i=0;i<line1.size();i++){
                String data1=line1.get(i);
                System.out.println("検索対象:"+data1);
                for (int j=0;j<line2.size();j++){
                    String[] data2=line2.get(j).split(",");
                    System.out.println(data2[0]);
                    if(data1.equals(data2[0])){
                        System.out.println(data1 +":"+data2[0]);
                        int a=Integer.parseInt(data2[1]);
                        int b=Integer.parseInt(data2[2]);
                        Point p=new Point(a,b);
                        points.add(p);
                    }
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

//        try {
//            points.add(new Point(0,0));
//            // CSVファイルの読み込み
//            List<String> lines = Files.readAllLines(path1);
//            for (int i = 0; i < lines.size(); i++) {
//                String[] data = lines.get(i).split(",");
//                int a=Integer.parseInt(data[1]);
//                int b=Integer.parseInt(data[2]);
//                Point p=new Point(a,b);
//                points.add(p);
//            }
//        } catch (IOException e) {
//            System.out.println("ファイル読み込みに失敗");
//        }
        int n=points.size();
        System.out.println("nの大きさ:"+n);

        List<Point> resut=tsp(n,points);

        info(n,points);
        info(n,resut);

    }

    public static double distancePoint(Point p, Point q) {
        return Math.sqrt((p.x - q.x) * (p.x - q.x) + (p.y - q.y) * (p.y - q.y));
    }

    public static double distanceRoot(int n,List<Point> route){
        double sum=0;
        for(int i=0;i<n-1;i++){
            sum += distancePoint(route.get(i),route.get(i+1));
        }
        return sum;
    }

    public static List<Point> tsp(int n ,List<Point> points){
        List<Point> result =new ArrayList<>();
        List<Point> remaining=new ArrayList<>(points);
        result.add(remaining.remove(0));

        while (!remaining.isEmpty()){
            Point a=remaining.get(remaining.size()-1);
            Point b=null;

            for(Point p :remaining){
                if(b==null || distancePoint(a,p)<distancePoint(a,b)){
                    b=p;
                }
            }
            result.add(b);
            remaining.remove(b);
        }
        return result;
    }


    public static void info(int n,List<Point> route){
        for(int i=0;i<n;i++){
            System.out.println(route.get(i).x+" "+route.get(i).y);
        }
        System.out.println("distanceRoot :" +distanceRoot(n,route));
    }



}

