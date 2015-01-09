# expect to wait for 10 minutes or so
mkdir data/train_resized
mogrify -resize 250x250 -background white -gravity center -extent 250x250 -path ../train_resized data/train/*.jpg
#mkdir data/test_resized
#mogrify -resize 250x250 -background white -gravity center -extent 250x250 -path ../test_resized data/test1/*.jpg

