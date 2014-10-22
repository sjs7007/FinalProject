#install.packages("kernlab")

library(kernlab)

x <- c(0,0,1,1)
y <- c(0,1,0,1)
class <- factor(c(1,-1,-1,1))
d = data.frame(x=x,y=y,class=class)

model.ksvm = ksvm(class ~ x + y , data =d ,type="C-svc")
plot(model.ksvm,data=d)
savePlot("XORClassification2.png")






