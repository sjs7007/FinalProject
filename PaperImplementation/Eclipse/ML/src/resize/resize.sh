# expect to wait for 10 minutes or so
mkdir /home/shinchan/Downloads/zipFiles/trainResized
mogrify -resize 250x250 -background white -gravity center -extent 250x250 -path /home/shinchan/Downloads/zipFiles/trainResized /home/shinchan/Downloads/zipFiles/train/*.jpg
#mkdir data/test_resized
#mogrify -resize 250x250 -background white -gravity center -extent 250x250 -path data/test_resized data/test1/*.jpg

