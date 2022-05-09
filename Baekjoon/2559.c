#include <stdio.h>

int main()
{
    int n, m, number;
    scanf("%d %d", &n, &m);
    int num[n+1];
    int sum = 0;
    int max = -100000;
    num[0] = sum;
    for(int i=1;i<=n;i++){
        scanf("%d", &number);
        sum = sum + number;
        num[i] = sum;
    }
    for(int i=0;i<=n-m;i++){
        if(max < num[i+m] - num[i]){
            max = num[i+m] - num[i];
        }
    }
    printf("%d ", max);
}
