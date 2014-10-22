concrete <-read.csv("concrete.csv")
str(concrete)

par(mfrow=c(3,3))                                                                                                             
plot(concrete$cement)     
plot(concrete$slag)  
plot(concrete$ash)   
plot(concrete$water) 
plot(concrete$superplastic)
plot(concrete$coarseagg)   
plot(concrete$fineagg)   
plot(concrete$age)   
plot(concrete$strength)       
	
	
normalize <-function(x)
{
	return((x-min(x))/(max(x)-min(x)))
}


concrete_norm <- as.data.frame(lapply(concrete,normalize));


concrete_train <- concrete_norm[1:773, ]
concrete_test <- concrete_norm[774:1030, ]

#install.packages("neuralnet")
library(neuralnet)
		
concrete_model <- neuralnet(strength ~ cement + slag + ash + water + superplastic + coarseagg + fineagg + age, data = concrete_train)
plot(concrete_model)
savePlot("neuralArch.png")

model_results <- compute(concrete_model, concrete_test[1:8])
predicted_strength <- model_results$net.result
cor(predicted_strength, concrete_test$strength)

concrete_model2 <- neuralnet(strength ~ cement + slag + ash + water + superplastic + coarseagg + fineagg + age, data = concrete_train, hidden = 5)
plot(concrete_model2)
savePlot("neuralArch2.png")

model_results2 <- compute(concrete_model2, concrete_test[1:8])
predicted_strength2 <- model_results2$net.result
cor(predicted_strength2, concrete_test$strength)

