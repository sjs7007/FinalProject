#Tutorial From : http://trevorstephens.com/post/72918760617/titanic-getting-started-with-r-part-1-booting-up-r

#change working directory
setwd("~/MachineLearning/TitanicKaggle")
#import the training set
train <- read.csv("~/MachineLearning/TitanicKaggle/train.csv")

#import the test set
test <- read.csv("~/MachineLearning/TitanicKaggle/test.csv")

#set all to 0, i.e. predicting that all die
test$Survived <- rep(0, 418)

#create data frame with titles same as that of kaggle format
submit <- data.frame(PassengerId = test$PassengerId, Survived = test$Survived)

#stored data in submit in CSV format and excluded row numbers
write.csv(submit, file = "theyallperish.csv", row.names = FALSE)
