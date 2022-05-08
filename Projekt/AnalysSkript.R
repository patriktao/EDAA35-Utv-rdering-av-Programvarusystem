source("https://fileadmin.cs.lth.se/cs/Education/EDAA35/R_resources.R")

plotresult <- function(file, start = 1) {
  data <- read.csv(file)
  data <- data[start:nrow(data),]
  #plot(data$mergeSort, type="l")
  plot(data$quickSort, type="l")
  #return(mean(data$javaSortingAlgorithm))
  return(mean(data$quickSort))
}

meanValue <- function(name){
  meanValues <- vector()
  meanValues <- append(meanValues, plotresult(name))
  mean <- mean(meanValues)
  median <- median(meanValues)
  cI <- confidenceInterval(meanValues, confidenceLevel=0.95)
  temp <- c(summary(meanValues), cI)
  return(temp)
}


tValues <- function(counter){
  avgJava <- vector()
  avgMySort <- vector()
  for(i in 1:counter){
    data <- read.csv("result.txt")
    data <- data[1:nrow(data),]
    avgJava <- append(avgJava, mean(data$javaSortingAlgorithm))
    avgMySort <- append(avgMySort, mean(data$mySortingAlgorithm))
  }
  tTest <- t.test(avgJava, avgMySort)
  return(tTest)
}


