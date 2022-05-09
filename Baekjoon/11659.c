#include <stdio.h>

int main()
{
    int n, m, number;
    scanf("%d %d", &n, &m);
    int num[n+1];
    int sum = 0;
    num[0] = sum;
    for(int i=1;i<=n;i++){
        scanf("%d", &number);
        sum = sum + number;
        num[i] = sum;
    }
    int i, j;
    for(int k=0;k<m;k++){
        scanf("%d %d", &i, &j);
        printf("%d\n", num[j] - num[i-1]);
    }
}
