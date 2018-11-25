.class public Lcom/example/user/logintest/MainActivity;
.super Landroid/app/Activity;
.source "MainActivity.java"


# instance fields
.field private mLogInButton:Landroid/widget/Button;

.field private mPasswordEditText:Landroid/widget/EditText;

.field private mStatusTextView:Landroid/widget/TextView;


# direct methods
.method public constructor <init>()V
    .locals 0

    .prologue
    .line 13
    invoke-direct {p0}, Landroid/app/Activity;-><init>()V

    return-void
.end method

.method static synthetic access$000(Lcom/example/user/logintest/MainActivity;)Landroid/widget/EditText;
    .locals 1
    .param p0, "x0"    # Lcom/example/user/logintest/MainActivity;

    .prologue
    .line 13
    iget-object v0, p0, Lcom/example/user/logintest/MainActivity;->mPasswordEditText:Landroid/widget/EditText;

    return-object v0
.end method

.method static synthetic access$100(Lcom/example/user/logintest/MainActivity;Ljava/lang/String;)Z
    .locals 1
    .param p0, "x0"    # Lcom/example/user/logintest/MainActivity;
    .param p1, "x1"    # Ljava/lang/String;

    .prologue
    .line 13
    invoke-direct {p0, p1}, Lcom/example/user/logintest/MainActivity;->passwordCheck(Ljava/lang/String;)Z

    move-result v0

    return v0
.end method

.method static synthetic access$200(Lcom/example/user/logintest/MainActivity;)V
    .locals 0
    .param p0, "x0"    # Lcom/example/user/logintest/MainActivity;

    .prologue
    .line 13
    invoke-direct {p0}, Lcom/example/user/logintest/MainActivity;->processLogInSuccess()V

    return-void
.end method

.method static synthetic access$300(Lcom/example/user/logintest/MainActivity;)V
    .locals 0
    .param p0, "x0"    # Lcom/example/user/logintest/MainActivity;

    .prologue
    .line 13
    invoke-direct {p0}, Lcom/example/user/logintest/MainActivity;->processLogInFail()V

    return-void
.end method

.method private passwordCheck(Ljava/lang/String;)Z
    .locals 1
    .param p1, "password"    # Ljava/lang/String;

    .prologue
    .line 41
    const-string v0, "possword"

    invoke-virtual {v0, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 42
    const/4 v0, 0x1

    .line 44
    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method private processLogInFail()V
    .locals 2

    .prologue
    .line 54
    iget-object v0, p0, Lcom/example/user/logintest/MainActivity;->mStatusTextView:Landroid/widget/TextView;

    const-string v1, "\u30d1\u30b9\u30ef\u30fc\u30c9\u304c\u7570\u306a\u308a\u307e\u3059"

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 55
    const-string v0, "\u30d1\u30b9\u30ef\u30fc\u30c9\u304c\u7570\u306a\u308a\u307e\u3059"

    const/4 v1, 0x1

    invoke-static {p0, v0, v1}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v0

    invoke-virtual {v0}, Landroid/widget/Toast;->show()V

    .line 56
    return-void
.end method

.method private processLogInSuccess()V
    .locals 2

    .prologue
    .line 49
    iget-object v0, p0, Lcom/example/user/logintest/MainActivity;->mStatusTextView:Landroid/widget/TextView;

    const-string v1, "\u30ed\u30b0\u30a4\u30f3\u3057\u307e\u3057\u305f"

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 50
    const-string v0, "\u30ed\u30b0\u30a4\u30f3\u3057\u307e\u3057\u305f"

    const/4 v1, 0x1

    invoke-static {p0, v0, v1}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v0

    invoke-virtual {v0}, Landroid/widget/Toast;->show()V

    .line 51
    return-void
.end method


# virtual methods
.method protected onCreate(Landroid/os/Bundle;)V
    .locals 2
    .param p1, "savedInstanceState"    # Landroid/os/Bundle;

    .prologue
    .line 21
    invoke-super {p0, p1}, Landroid/app/Activity;->onCreate(Landroid/os/Bundle;)V

    .line 22
    const v0, 0x7f04001a

    invoke-virtual {p0, v0}, Lcom/example/user/logintest/MainActivity;->setContentView(I)V

    .line 24
    const v0, 0x7f0b0055

    invoke-virtual {p0, v0}, Lcom/example/user/logintest/MainActivity;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/EditText;

    iput-object v0, p0, Lcom/example/user/logintest/MainActivity;->mPasswordEditText:Landroid/widget/EditText;

    .line 25
    const v0, 0x7f0b0057

    invoke-virtual {p0, v0}, Lcom/example/user/logintest/MainActivity;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    iput-object v0, p0, Lcom/example/user/logintest/MainActivity;->mStatusTextView:Landroid/widget/TextView;

    .line 27
    const v0, 0x7f0b0056

    invoke-virtual {p0, v0}, Lcom/example/user/logintest/MainActivity;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/Button;

    iput-object v0, p0, Lcom/example/user/logintest/MainActivity;->mLogInButton:Landroid/widget/Button;

    .line 28
    iget-object v0, p0, Lcom/example/user/logintest/MainActivity;->mLogInButton:Landroid/widget/Button;

    new-instance v1, Lcom/example/user/logintest/MainActivity$1;

    invoke-direct {v1, p0}, Lcom/example/user/logintest/MainActivity$1;-><init>(Lcom/example/user/logintest/MainActivity;)V

    invoke-virtual {v0, v1}, Landroid/widget/Button;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 38
    return-void
.end method
