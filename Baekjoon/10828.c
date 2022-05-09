/* 10828 */

#include <stdio.h>
#include <string.h>

int main()
{
    int n;
    int num;
    int length = -1;
    int stack[10001];
    char code[50];
    scanf("%d", &n);

    for(int i=0;i<n;i++){
        scanf("%s", code);
        if(strcmp(code, "push") == 0){
            scanf("%d", &num);
            length = length + 1;
            stack[length] = num;
        }
        else if(strcmp(code, "pop") == 0){
            if(length != -1){
                printf("%d\n", stack[length]);
                length = length - 1;
            }
            else{
                printf("-1\n");
            }
        }
        else if(strcmp(code, "size") == 0){
            printf("%d\n", length+1);
        }
        else if(strcmp(code, "empty") == 0){
            if(length != -1){
                printf("0\n");
            }
            else{
                printf("1\n");
            }
        }
        else if(strcmp(code, "top") == 0){
            if(length != -1){
                printf("%d\n", stack[length]);
            }
            else{
                printf("-1\n");
            }
        }
    }
}
