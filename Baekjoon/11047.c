#include <stdio.h>

int main()
{
    int n, k;
    int count = 0;
    scanf("%d %d", &n, &k);
    int size[n];
    for(int i=0;i<n;i++){
        scanf("%d", &size[i]);
    }
    for(int i=n-1;i>=0;i--){
        if(k == 0){
            break;
        }
        if(k/size[i] >= 1){
            count = count + k/size[i];
            k = k%size[i];
        }
    }
    printf("%d\n", count);
}
