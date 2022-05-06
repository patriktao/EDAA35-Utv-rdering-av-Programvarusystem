source("~/Projects/EDAA35/Lab2/lab.r")
a <- c(12, NA, 10, NA)
b <- c(NA, NA, 15, 20)
df <- data.frame(a, b)
print(removeNA(df))
print(nrow(removeNA(data)))

function(data)
na.omit(data)
