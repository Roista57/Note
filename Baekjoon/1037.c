#include <stdio.h>
#include <stdlib.h>

int compare(const void *a, const void *b){
    int num1 = *(int *)a;
    int num2 = *(int *)b;
    if(num1 < num2) return -1;
    if(num1 > num2) return 1;
    return 0;
}

int main()
{
    int n;
    scanf("%d", &n);
    int num[n];
    for(int i=0;i<n;i++){
        scanf("%d", &num[i]);
    }
    if(n == 1){
        printf("%d\n", num[0]*num[0]);
    }
    else{
        qsort(num, sizeof(num) / sizeof(int), sizeof(int), compare);
        printf("%d\n", num[0]*num[n-1]);
    }
}
