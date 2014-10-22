#Tutorial 2 From : http://trevorstephens.com/post/72918760617/titanic-getting-started-with-r-part-1-booting-up-r

#change working directory
setwd("~/MachineLearning/TitanicKaggle")
#import the training set
train <- read.csv("~/MachineLearning/TitanicKaggle/train.csv")

#import the test set
test <- read.csv("~/MachineLearning/TitanicKaggle/test.csv")

#set all to 0, i.e. predicting that all die
test$Survived <- 0 #same as using rep

#give genderwise data
summary(train$Sex)
prop.table(table(train$Sex, train$Survived),1)

#change all female predictions to will survive
test$Survived[test$Sex == 'female'] <- 1

#create data frame with titles same as that of kaggle format
submit <- data.frame(PassengerId = test$PassengerId, Survived = test$Survived)

#stored data in submit in CSV format and excluded row numbers
write.csv(submit, file = "AllMenMustDie.csv", row.names = FALSE)

#add new column to training data
train$Child <- 0

#if age <18, set child column to 1
train$Child[train$Age < 18] <- 1

#gives percentage of people belonging to each subset who survived 
aggregate(Survived ~ Child + Sex,data=train,FUN=function(x){100*sum(x)/length(x)})

#next, we create a new column to categorize people according the fare amount 
train$Fare2<-'30+'
train$Fare2[train$Fare>=20 & train$Fare<30]<-'20-30'
train$Fare2[train$Fare>=10 & train$Fare<20]<-'10-20'
train$Fare2[train$Fare<10]<-'<10'

#check for new subsets
aggregate(Survived ~ Fare2 + Pclass + Sex,data=train,FUN=function(x){100*sum(x)/length(x)})

#females belonging to class 3 and having paid 30+ also have low rate
test$Survived <- 0
test$Survived[test$Sex == 'female'] <- 1
test$Survived[test$Sex == 'female' & test$Pclass == 3 & test$Fare >= 20] <- 0

#create submission file
submit<-data.frame(PassengerId=test$PassengerId,Survived=test$Survived)
write.csv(submit,file="genderClassModel.csv",row.names=FALSE)
