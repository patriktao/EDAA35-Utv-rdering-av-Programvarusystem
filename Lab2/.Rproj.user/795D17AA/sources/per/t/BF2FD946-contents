removeNA <- function(data) {
  na.omit(data)
}

analysePotentialOutliers <-function(data, threshold){  
  df <- data.frame()
  for (i in 1:(length(data))) {
    #Here we loop through the different variables
    col <- data[ ,i]  
    #All values which are under the threshold
    nol <- col[col<threshold[i]]
    #All values over the threshold
    ol <- col[col>threshold[i]]
    #Dataframe
    L <- c(colnames(data[i]),col[0], length(ol), mean(nol))
    #combine the frame and data
    df <- rbind(df, L)   
  }
  #Change column names
  colnames(df) <- c("Variable","NrPotentialOutliers", "MeanNoOutliers")
  return(df)
}

#read.delim("newfile.txt", header = TRUE, sep = "|")

contributors <- function(file, n){
  fileRead <- read.delim(file, header = TRUE, sep = "|")
  df <- data.frame()
  da <- data.frame()
  allNames <- unique(fileRead$user)
  
  for(i in 1:(length(allNames))){
    #name
    name <- unique(fileRead$user)[i]
    #most recent date
    date <- max(fileRead[fileRead$user == name,]$date)
    #substirng
    date <- substring(date, first=2, last=11)
    #total rows of commits
    totalRows <- nrow(fileRead[fileRead$user == name,])
    #create data frame
    L <- c(name, totalRows)
    N <- c(name, date)
    #add it into the frame
    df <- rbind(df, L)
    da <- rbind(da, N)
  }
  #Column Names
  colnames(df) <- c("Contributor", "Commits")
  colnames(da) <- c("Contributor", "Date")
  #convert to numreric vector for the commits
  df$Commits <- as.numeric(as.character(df$Commits))
  #Now we can sort based on commits
  df <- df[order(-df$Commits),][1:n,]
  da <- da[rev(order(as.Date(da$Date, format="%m/%d/%Y"))),][1:n,]
  #Convert to Vector
  barplot(unlist(df[2]), names.arg=unlist(df[1]))
  return(da)
}


