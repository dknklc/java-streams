# JAVA STREAMS

## 1 Introduction

Keywords: Streams, File Operations

In the challenge, you are asked to analyze the data collected for market research on various dates.

## 2 Overview

The data is collected under a csv file whose its fields are separated by commas. The file
contains information about:

- Age of the customer on the purchase date.
- The purchase date.
- Price paid of 5 different products, named from A to E. Empty field means the customer
  did not buy the product on that date.
  So the header of the csv file is:
  Age,Date,A,B,C,D,E
  Your task is to write a program that answers the questions in the following section. The
  csv file will be given as an argument to your program:

java YourProgramNamecsv_file_name question_no
And it should print the answer of the given question. You can find an example file on
ODTUClass.

## 3 Questions

You can find the questions your program should answer below:

1. What was the price of most expensive product sold?
2. What was the date of the highest paid purchase by a customer?
3. What was the most popular product before 2000?
4. What was the least popular product after and including 2000?
5. What was the most popular product among teens? (Hint: find the product with the
   smallest average customer age)
6. What was the most inflated product on the data? When calculating only consider the
   oldest and the newest purchase and not the purchases between. As an exercise try to
   solve this question by reading the stream only once.

## 4 Some Remarks

- You can assume that there will be no purchase older than 1970 and newer than 2020.
- Make sure that your program outputs the results in correctly named files.
- Use Java 8 or a newer version.
