#include <stdio.h>

int main()
{
    int a, b, len;
    int max = 0;
    int count = 1;
    scanf("%d %d", &a, &b);
    if(a > b){
        len = a;
    }
    else {
        len = b;
    }
    for(int i=1;i<=len;i++){
        if(a%i == 0 && b%i == 0){
            if(max < i){
                max = i;
            }
        }
    }
    printf("%d\n", max);
    printf("%d\n", max * a/max * b/max);
}
