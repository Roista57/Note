/* 10773 */
#include <stdio.h>

int main()
{
    int n, number;
    int sum = 0;
    int length = -1;
    scanf("%d", &n);
    int num[n];
    for(int i=0;i<n;i++){
        scanf("%d", &number);
        if(number != 0){
            length = length + 1;
            num[length] = number;
        }
        else if(number == 0 && length != -1){
            length = length - 1;
        }
    }
    if(length != -1){
        for(int i=0;i<=length;i++){
            sum += num[i];
        }
    }
    printf("%d\n", sum);
}
