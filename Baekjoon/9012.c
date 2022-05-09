/* 9012 */

#include <stdio.h>
#include <string.h>

int change(char st[])
{
    int count = 0;
    int size = strlen(st);
    int check[size];

    if(st[0] == ')'){
        return -1;
    }else if(st[size-1] == '('){
        return -1;
    }
    else{
        for(int i=0;i<size;i++){
            if(st[i] == '('){
                check[i] = count;
                count++;
            }
            else if(st[i] == ')'){
                count--;
                if(count < 0){
                    return -1;
                }
                else{
                    check[i] = count;
                }
            }
        }
        if(check[0] != check[size-1]){
            return -1;
        }
    }
    return 1;
}

int main()
{
    int n;
    char st[50];
    scanf("%d", &n);
    for(int i=0;i<n;i++){
        scanf("%s", st);
        if(change(st) == -1){
            printf("NO\n");
        }
        else if(change(st) == 1){
            printf("YES\n");
        }
    }
}
