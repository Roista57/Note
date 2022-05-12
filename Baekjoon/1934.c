#include <stdio.h>

int main()
{
    int a, b, len;
    int max;
    int t;
    scanf("%d", &t);
    for(int i=0;i<t;i++){
        max = 0;
        scanf("%d %d", &a, &b);
        if(a > b){
            len = a;
        }
        else {
            len = b;
        }
        for(int j=1;j<=len;j++){
            if(a%j == 0 && b%j == 0){
                if(max < j){
                    max = j;
                }
            }
        }
        printf("%d\n", max * a/max * b/max);
    }
}
