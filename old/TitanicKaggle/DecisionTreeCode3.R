#Part 3 of Tutorial using Decision Trees

#importing package needed for the decision tree
library(rpart)

#call rpart. Syntax is similar to aggregate function.
fit <- rpart(Survived ~ Pclass + Sex + Age + SibSp + Parch + Fare + Embarked, data=train, method="class")


#plot(fit)
#text(fit)
#this works but not as pretty. For pretty tree, use below code

#once installed, below portion can be commented out
#install.packages('rattle')
#install.packages('rpart.plot')
#install.packages('RColorBrewer')

#import packages
library(rattle)
library(rpart.plot)
library(RColorBrewer)

fancyRpartPlot(fit)

#actually make prediction and store
Prediction <- predict(fit, test, type="class")
submit <- data.frame(PassangerId = test$PassengerId, Survived = Prediction)
write.csv(submit, file = "DecisionTreeCode3.csv",row.names = FALSE)
