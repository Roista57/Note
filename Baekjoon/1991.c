#include <stdio.h>
#include <string.h>

struct tree{
    char data;
    struct tree *left;
    struct tree *right;
};

struct tree *create_tree(char data, struct tree *left, struct tree *right){
    struct tree *newnode = (struct tree*)malloc(sizeof(struct tree));
    newnode->data = data;
    newnode->left = left;
    newnode->right = right;
    return newnode;
};

void preorder(struct tree *n);
void inorder(struct tree *n);
void postorder(struct tree *n);

int main()
{
    struct tree *node[30];
    struct tree *L;
    struct tree *R;
    int n;
    scanf("%d", &n);
    char list[n][3];
    for(int i=0;i<n;i++){
        getchar();
        scanf("%c", &list[i][0]);
        getchar();
        scanf("%c", &list[i][1]);
        getchar();
        scanf("%c", &list[i][2]);
    }

    for(int i=0;i<n-1;i++){
        for(int j=i+1;j<n;j++){
            if(list[i][0] > list[j][0]){
                char temp[3];
                memcpy(temp, list[i], sizeof(char) * 3);
                memcpy(list[i], list[j], sizeof(char) * 3);
                memcpy(list[j], temp, sizeof(char) * 3);
            }
        }
    }

    for(int i=n-1;i>=0;i--){
        if(list[i][1] == '.'){ L = NULL; }
        else{ L = node[(list[i][1] - 'A')]; }
        if(list[i][2] == '.'){ R = NULL; }
        else{ R = node[(list[i][2] - 'A')]; }
        node[i] = create_tree(list[i][0], L, R);
    }

    preorder(node[0]); printf("\n");
    Inorder(node[0]); printf("\n");
    postorder(node[0]);
}

void preorder(struct tree *n){
    if(n != NULL){
        printf("%c", n->data);
        preorder(n->left);
        preorder(n->right);
    }
}

void Inorder(struct tree *n){
    if(n != NULL){
        Inorder(n->left);
        printf("%c", n->data);
        Inorder(n->right);
    }
}

void postorder(struct tree *n){
    if(n != NULL){
        postorder(n->left);
        postorder(n->right);
        printf("%c", n->data);
    }
}
