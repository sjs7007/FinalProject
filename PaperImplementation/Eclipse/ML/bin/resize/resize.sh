# expect to wait for 10 minutes or so
mkdir /home/shinchan/Downloads/zipFiles/testResized
mogrify -resize 250x250 -background white -gravity center -extent 250x250 -path /home/shinchan/Downloads/zipFiles/testResized /home/shinchan/Downloads/zipFiles/test/*.jpg
#mkdir data/test_resized
#mogrify -resize 250x250 -background white -gravity center -extent 250x250 -path data/test_resized data/test1/*.jpg

