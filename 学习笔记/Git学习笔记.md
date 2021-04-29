# Git简介

Git是世界上最先进的分布式版本控制系统，没有之一。

Git的安装可以自己去百度教程，这里不多叙述。

## 创建版本库

1. 创建一个空目录： 

   ```
   mkdir learngit
   cd learngit
   pwd  // 用来显示当前目录路径的
   git init // 用来创建版本库的命令，对该目录进行初始化
   ```

   git init之后，就把Git仓库创建好了，而且是一个空的仓库，当前目录下多了一个.git目录，这个目录是Git用来跟踪管理版本库的，没事可以不用修改里面的文件。

   .git目录默认是隐藏的，如果看不到，可以使用 **ls -ah** 命令。

   **注意：** 所有的版本控制系统，其实只能跟踪文本文件的改动，比如TXT文件，网页，所有的程序代码等，Git也是如此。版本控制系统可以告诉你每次的改动，比如在第五行加了一个单词“linux”。但是，图片、视频这些二进制文件，虽然也能有版本控制系统管理，但是没办法跟踪具体的变化，只能把每次的改动串在一起。可能知道图片从100KB 改成了120KB，但是到底改了什么，版本控制系统不知道，也没办法知道。

2. 编写一个readme.txt文件,内容如下

   ```
   Git is a version control system.
   Git is free software.
   ```

   第一步：使用命令**git add**  把文件添加到仓库

   第二部：使用命令**git commit** 告诉Git，把文件提交到仓库。 后面的 -m是本次提交的说明。

   ```
   git add readme.txt
   git add .   后面加一个 . 表示提交被修改的新创建的文件，但是不包括删除的文件
   git add -u --update  更新所有改变的文件，既提交所有改变的文件
   git add -A --all 提交已被修改和已被删除的文件，但是不包括新的文件
   git commit -m "write a readme file"
   ```

   注意：使用git add 可以添加不同的文件，使用git commit一次可以提交很多文件。

   例如:

   ```
   git add file.txt file2.txt
   git commit -m "write two file"
   ```

## 时光穿梭机

1. 修改readme.txt文件内容

   ```
   Git is a distributed version control system.
   Git is free software.
   ```

   使用**git status** 命令查看结果。

![截屏2021-04-28 下午4.26.31](/Users/hanfeixiang/Library/Application Support/typora-user-images/截屏2021-04-28 下午4.26.31.png)

**git status** 命令可以让我们时刻掌握仓库当前的状态，上面命令告诉我们有三个文件被删除了，一个文件被修改了，但是还没有准备提交修改。

**git diff** 命令用来查看修改了什么内容

![截屏2021-04-28 下午4.30.17](/Users/hanfeixiang/Library/Application Support/typora-user-images/截屏2021-04-28 下午4.30.17.png)

使用git add把文件提交到仓库,使用git add之后可以继续使用**git status** 查看当前的状态，然后在使用git commit命令提交文件，之后可以在使用git status来查看状态。

### 版本回退

1. 继续修改readme文件

   ```
   Git is a distributed version control system.
   Git is free software distributed under the GPL.
   ```

   再把文件进行添加和提交，到这里，文件一共被添加了三次，可以使用**git log** 命令来查看提交的记录。可以显示从最近到最远的提交记录。

<img src="/Users/hanfeixiang/Library/Application Support/typora-user-images/截屏2021-04-28 下午4.43.16.png" alt="截屏2021-04-28 下午4.43.16" style="zoom:50%;" />

​    commit后面的一大串数字是版本号码。

2. 使用**git reset -- hard HEAD^** 命令回退到上一个版本。在git中 HEAD表示当前版本，上一个版本就是**HEAD^** ,上上个版本就是**HEAD^^**, 往上数100个版本就是**HEAD~100** .

   <img src="/Users/hanfeixiang/Library/Application Support/typora-user-images/截屏2021-04-28 下午5.49.53.png" alt="截屏2021-04-28 下午5.49.53" style="zoom:50%;" />

3. 如果想要在回到刚才的版本，需要找到append GPL版本的commit id,然后利用命令

   **git reset --hard 57c2d** ,不需要全部写完，只需要写前面几个就可以了。

   <img src="/Users/hanfeixiang/Library/Application Support/typora-user-images/截屏2021-04-28 下午6.04.37.png" alt="截屏2021-04-28 下午6.04.37" style="zoom:50%;" />

4. 如果你第二天的时候在想进行版本回退，那时已经找不到append GPL版本的commit id了，这是需要利用**git reflog** 来查看每一次命令，然后找到append GPL的commit id.

   <img src="/Users/hanfeixiang/Library/Application Support/typora-user-images/截屏2021-04-28 下午6.05.05.png" alt="截屏2021-04-28 下午6.05.05" style="zoom:50%;" />

### 工作区和暂存区

1. 工作区：learngit文件夹就算是工作区
2. .git文件是Git的版本库，不算事工作区，Git版本库里面存了很多东西，其中最重要的就是称为stage（或者叫index）的暂存区，还有Git为我们自动创建的第一个分支master，以及指向master的一个指针叫HEAD.
3. 使用git add命令把文件添加进去时，实际上就是把文件修改添加到暂存区。使用git commit提交更改，实际上就是把暂存区的所有内容提交到当前分支。

### 管理修改

1. Git跟踪管理的是修改，并非文件。

2. 我们可以做如下操作

   第一次修改->git add ->第二次修改 -> git commit

   操作之后会发现第二次修改并没有被提交，因为你只把第一次的修改放入暂存区了，第二次的修改并没有被放到暂存区，所以，第二次的修改并没有被提交。

3. 可以使用**git duff HEAD -- readme.txt** 命令来查看工作区和版本库里面最新版本的区别。

### 撤销修改

1. 如果你在文件中写错了某个地方，但是你及时的发现了，就可以很容易的纠正他。删掉最后一行，手动把文件恢复到上一个版本的状态。
2. 如果使用git status看一下，git会告诉你可以丢弃工作区的修改，使用**git checkout -- file** 
   1. 这里有两种情况，一种是修改了还没有被放到暂存区。
   2. 一种是已经添加到暂存区了，然后又做了修改，使用命令之后就会到添加暂存区之后的状态。
3. 如果你的修改已经被提交到暂存区了，可以使用命令**git reset HEAD file** 可以把暂存区的修改撤销掉，重新放回工作区。
4. 如果你写错了东西，而且提交到了版本库，可以直接版本回退。
5. 如果你提交了版本库，还push到了远程，那你就死定了。 

### 删除文件

删除文件，首先添加一个test.txt文件到Git并提交.

```
git add test.txt
git commit -m "add test.txt"
```

此时，如果在工作区文件夹中删除文件，比如把test.txt文件删除了。**rm test.txt** 命令删除

这时，Git知道你已经删除了文件，因此，工作区和版本库就不一致了，git status会立刻告诉你哪些文件被删了。

+ 此时如果你真的想删除这个文件，可以使用命令**git rm test.txt** 在版本库删除。
+ 如果误删了，可以使用**git checkout -- test.txt** 来恢复文件。  

## 添加远程仓库

1. 登陆Github，然后，Create a new repo，创建一个新的仓库。

2. 根据github的提示，在本地的learngit仓库下运行命令

   ```
   git remote add origin git@github.com:overfat/learngit.git
   ```

3. 远程仓库的名字就叫origin，这是Git默认的叫法，也可以改成别的，但是origin这个名字一看就知道是远程库。

4. 把本地的内容推送到远程仓库上，git push -u origin master,由于远程库是空的，我们第一次推送master分支时，加上了-u参数，Git不但会把本地的master分支内容推送到远程新的master分支，还会把本地的分支和远程的master分支关联起来，在以后的推送或者拉取时就可以简化命令。

5. 第一次之后，在提交内容，通过命令: git push origin master

6. 当你第一次连接Github时，会出现SSH警告，这是正常现象，填写yes就好。

7. 如果添加的生活地址写错了，或者想删除远程仓库，可以使用如下命令、

   ```
   git remote rm <name>
   删除之前可以先使用 git remote -v查看一下远程仓库的关联信息。
   ```

8. 从远程克隆，git clone git@github.com:overfat/learngit.git

## 分支管理

### 创建分支与合并

每次提交，Git都把他们串成一条时间线，这条时间线就是一个分支，截止到目前，只有一条时间线，在Git里，我们叫做主分支，即master分支。HEAD严格来说不是指向提交，而是指向master，master才是指向提交的，所以，HEAD指向的就是当前分支。

1. 当我们创建新的分支时，例如dev时，在Git上就新建了一个指针叫dev，指向master相同的提交，在把HEAD指向dev，就表示当前分支在dev上。

   ```
   git checkout -b dev  git checkout命令加上-b参数表示创建并切换，相当于以下两条命令
   git branch dev  创建分支
   git checkout dev 切换分支
   
   git branch 查看当前分支，会列出所有分支，在当前分支前有*号
   ```

2. 接下来，我们可以对readme.txt文件进行修改，然后在提交

   ```
   git add readme.txt
   git commit -m "branch test"
   ```

3. 现在，dev分支工作完成，我们需要重新切换到master提交

   ```
   git checkout master  切换回主分支，如果这时在查看readme的内容，发现之前的改变没有了
   git merge dev   git merge命令用于合并指定分支到当前分支。合并之后，可以在查看readme.txt的内容。
   git branch -d dev 删除分支
   ```

4. 切换分支 switch

   ```
   git switch -c dev 使用switch创建并切换分支
   git switch master 直接切换到已有的分支
   ```

   



















