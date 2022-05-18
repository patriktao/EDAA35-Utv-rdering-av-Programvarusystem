source("https://fileadmin.cs.lth.se/cs/Education/EDAA35/R_resources.R")


createPlots <- function(file, name) {
  data <- read.csv(file)
  data <- data[1:nrow(data),]
  png(name)
  par(mfrow=c(2,1))
  plot(data$mergeSort,  type="l", main="MergeSort", xlab="Iterations", ylab="Time (ns)", col="blue")
  plot(data$quickSort, type="l", main="QuickSort", xlab="Iterations", ylab="Time (ns)", col="darkgreen")
  dev.off()
  "finished creating plots"
  #plot(data$mergeSort,  type="l", xlab="Iterations", ylab="Time (ns)", col="blue", frame=TRUE)
  #lines(data$quickSort, type="l", xlab="Iterations", ylab="Time (ns)", col="red")
  #legend("topright", legend=c("MergeSort", "QuickSort"),
  #       col=c("blue", "red"), lty = 1:1, cex=0.8)
  #dev.off()
  "finished creating plots"
}

meanSortingValues <- function(file, start=1){
  data <- read.csv(file)
  data <- data[start:nrow(data),]
  return(mean(data$mergeSort))
}

meanValue <- function(counter){
  meanValues <- vector()
  for(i in 1:counter){
    system("java IntegerSort data_random_1000 res_random_1000 500")
    meanValues <- append(meanValues, meanSortingValues("res_random_1000"))
  }
  mean <- mean(meanValues)
  median <- median(meanValues)
  cI <- confidenceInterval(meanValues, confidenceLevel=0.95)
  temp <- c(summary(meanValues), cI)
  return(temp)
}

tValues <- function(counter){
  avgMerge <- vector()
  avgQuick <- vector()
  for(i in 1:counter){
    system("java IntegerSort data_semi-reverse_500 res_semi-reverse_500 100")
    data <- read.csv("res_semi-reverse_500")
    data <- data[1:nrow(data),]
    avgMerge <- append(avgMerge, mean(data$mergeSort))
    avgQuick <- append(avgQuick, mean(data$quickSort))
  }
  tTest <- t.test(avgMerge, avgQuick)
  return(tTest)
}


