#install.packages("e0171")

library(e1071)

x <- c(0,0,1,1)
y <- c(0,1,0,1)
class <- factor(c(1,-1,-1,1))
d = data.frame(x=x,y=y,class=class)

model = svm(class ~ x + y, data=d)
plot(model,d)
savePlot("XORClassification1.png")







