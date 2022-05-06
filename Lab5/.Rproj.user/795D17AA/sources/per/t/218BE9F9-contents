#med JIT

system("java -cp Lab5Java/bin ExecutionTime data1.txt result.txt 600")
system("java -cp Lab5Java/bin ExecutionTime data2.txt result.txt 600")
#utan JIT
system("java -Xint -cp Lab5Java/bin ExecutionTime data1.txt result.txt 600")
system("java -Xint -cp Lab5Java/bin ExecutionTime data2.txt result.txt 600")

source("https://fileadmin.cs.lth.se/cs/Education/EDAA35/R_resources.R")
confidenceInterval(data$mySortingAlgorithm, confidenceLeve=0.95)
confidenceInterval(data$javaSortingAlgorithm, confidenceLeve=0.95)

