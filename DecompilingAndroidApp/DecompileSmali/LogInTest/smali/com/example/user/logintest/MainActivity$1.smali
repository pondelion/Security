.class Lcom/example/user/logintest/MainActivity$1;
.super Ljava/lang/Object;
.source "MainActivity.java"

# interfaces
.implements Landroid/view/View$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/example/user/logintest/MainActivity;->onCreate(Landroid/os/Bundle;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/example/user/logintest/MainActivity;


# direct methods
.method constructor <init>(Lcom/example/user/logintest/MainActivity;)V
    .locals 0
    .param p1, "this$0"    # Lcom/example/user/logintest/MainActivity;

    .prologue
    .line 28
    iput-object p1, p0, Lcom/example/user/logintest/MainActivity$1;->this$0:Lcom/example/user/logintest/MainActivity;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/view/View;)V
    .locals 2
    .param p1, "view"    # Landroid/view/View;

    .prologue
    .line 31
    iget-object v0, p0, Lcom/example/user/logintest/MainActivity$1;->this$0:Lcom/example/user/logintest/MainActivity;

    iget-object v1, p0, Lcom/example/user/logintest/MainActivity$1;->this$0:Lcom/example/user/logintest/MainActivity;

    # getter for: Lcom/example/user/logintest/MainActivity;->mPasswordEditText:Landroid/widget/EditText;
    invoke-static {v1}, Lcom/example/user/logintest/MainActivity;->access$000(Lcom/example/user/logintest/MainActivity;)Landroid/widget/EditText;

    move-result-object v1

    invoke-virtual {v1}, Landroid/widget/EditText;->getText()Landroid/text/Editable;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v1

    # invokes: Lcom/example/user/logintest/MainActivity;->passwordCheck(Ljava/lang/String;)Z
    invoke-static {v0, v1}, Lcom/example/user/logintest/MainActivity;->access$100(Lcom/example/user/logintest/MainActivity;Ljava/lang/String;)Z

    move-result v0

    ##if-eqz v0, :cond_0

    .line 32
    iget-object v0, p0, Lcom/example/user/logintest/MainActivity$1;->this$0:Lcom/example/user/logintest/MainActivity;

    # invokes: Lcom/example/user/logintest/MainActivity;->processLogInSuccess()V
    invoke-static {v0}, Lcom/example/user/logintest/MainActivity;->access$200(Lcom/example/user/logintest/MainActivity;)V

    .line 36
    :goto_0
    return-void

    .line 34
    :cond_0
    iget-object v0, p0, Lcom/example/user/logintest/MainActivity$1;->this$0:Lcom/example/user/logintest/MainActivity;

    # invokes: Lcom/example/user/logintest/MainActivity;->processLogInFail()V
    invoke-static {v0}, Lcom/example/user/logintest/MainActivity;->access$300(Lcom/example/user/logintest/MainActivity;)V

    goto :goto_0
.end method
