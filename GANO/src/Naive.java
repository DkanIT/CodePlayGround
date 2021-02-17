public class Naive {
    public static void main(String[] args) {

        System.out.println(Naive(3,3));
    }

    public static int Naive(int a,int n){
        int res=1;
       if(n==0){
           return res;
       }
       else{
           res=a*Naive(a,n-1);
    }
       return res;
    }

    public static int power(int base, int powerRaised) {
        if (powerRaised != 0)
            return (base * power(base, powerRaised - 1));
        else
            return 1;
    }
}


