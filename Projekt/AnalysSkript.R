source("https://fileadmin.cs.lth.se/cs/Education/EDAA35/R_resources.R")


createPlots <- function(file, start = 1) {
  data <- read.csv(file)
  data <- data[start:nrow(data),]
  pdf("data_random_50.pdf")
  par(mfrow=c(2,1))
  plot(data$mergeSort,  type="l", main="MergeSort", xlab="Iterations", ylab="Time (ns)")
  plot(data$quickSort, type="l", main="QuickSort", xlab="Iterations", ylab="Time (ns)")
  dev.off()
  "finished creating plots"
}

meanSortingValues <- function(file, start=1){
  data <- read.csv(file)
  data <- data[start:nrow(data),]
  return(mean(data$quickSort))
}

meanValue <- function(counter){
  meanValues <- vector()
  for(i in 1:counter){
    system("java IntegerSort data_ordered_500 res_ordered_500 100")
    meanValues <- append(meanValues, meanSortingValues("res_ordered_500"))
  }
  mean <- mean(meanValues)
  median <- median(meanValues)
  cI <- confidenceInterval(meanValues, confidenceLevel=0.95)
  temp <- c(summary(meanValues), cI)
  return(temp)
}

tValues <- function(counter){
  avgHeap <- vector()
  avgMerge <- vector()
  for(i in 1:counter){
    data <- read.csv("result.txt")
    data <- data[1:nrow(data),]
    avgJava <- append(avgJava, mean(data$javaSortingAlgorithm))
    avgMySort <- append(avgMySort, mean(data$mySortingAlgorithm))
  }
  tTest <- t.test(avgJava, avgMySort)
  return(tTest)
}


