public class DrawHangman{
    public static void draw(int n){
        if(n == 6){

            System.out.println(" +----+    ");
            System.out.println(" |    |    ");
            System.out.println(" |         ");
            System.out.println(" |         ");
            System.out.println(" |         ");
            System.out.println("===========");
            
        }else if( n == 5){

            System.out.println(" +----+  ");
            System.out.println(" |    |  ");
            System.out.println(" |    O  ");
            System.out.println(" |         ");
            System.out.println(" |         ");
            System.out.println("===========");

        }else if( n == 4){

            System.out.println(" +----+    ");
            System.out.println(" |    |    ");
            System.out.println(" |    O    ");
            System.out.println(" |    |    ");
            System.out.println(" |         ");
            System.out.println("===========");

        }else if( n == 3){

            System.out.println(" +----+    ");
            System.out.println(" |    |    ");
            System.out.println(" |    O    ");
            System.out.println(" |   /|    ");
            System.out.println(" |         ");
            System.out.println("===========");

        }else if( n == 2){

            System.out.println(" +----+    ");
            System.out.println(" |    |    ");
            System.out.println(" |    O    ");
            System.out.println(" |   /|\\  ");
            System.out.println(" |         ");
            System.out.println("===========");

        }else if( n == 1){

            System.out.println(" +----+    ");
            System.out.println(" |    |    ");
            System.out.println(" |    O    ");
            System.out.println(" |   /|\\  ");
            System.out.println(" |   /     ");
            System.out.println("===========");

        }else if(n == 0){
            
            System.out.println(" +----+    ");
            System.out.println(" |    |    ");
            System.out.println(" |    O    ");
            System.out.println(" |   /|\\  ");
            System.out.println(" |   / \\  ");
            System.out.println("===========");
            System.out.println("  GAME OVER");
        }
    }
}