
# function for plotting graph
plotresult <- function(file, start = 1) {
  data <- read.csv(file)
  data <- data[start:nrow(data),]
  plot(data$mySortingAlgorithm, type="l")
}

#system("java -cp /Users/patriktao/Downloads/lab5/lab5/utfil.txt utfil.txt result1.txt 600")
#plotresult("result1.txt") # plot to screen

#pdf("result1.pdf")
#plotresult("result1.txt") #plot to pdf file
#dev.off()

