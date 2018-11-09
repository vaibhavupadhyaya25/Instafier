# Instafier
This project brings in the concept of a complete integrated system that works on Instagram and calculates the percentage of account's genuinity and fakeness, evaluates an account on factors like inactivity or bot account and segregates any given public account into normal, celebrity or promotional account.
----------------------------------------------------
#Working
The project works in 2 phases. Firstly we run all the python scrypts and store data in the database. secondly we access all the information from the database and feed it into servlets and format it in HTML. 
----------------------------------------------------
#Features
It checks on the basis of the following factor tests:

1. Ratio of Followers to Following: This factor calculates the ratio of the no of the followers
to the no of following and compares it top a threshold value that is 0.30. If it is greater
than that then the factor is true and flag is 1.

2. Whether account is Private or Public: It checks the privacy of the account and sets the
value of flag to 1 if it is private.

3. Account is Verified or not: It checks the certification of the account and sets the value
of flag to 1 if it is private.

4. Presence of profile picture: It scans the profile and checks if the user has a profile picture
or not and sets the value of flag.

5. Suspicious number of posts (very few or very large): It counts the no of posts a user has
and check for extremities. Very less posts indicates inactivity and greater no of posts
along with the dates involved shows usage of bot account.

6. Bio-token Match with corpus: It fetches the bio of the user through a php and stores it
into the database. It is extracted by a python script and then broken into separate words
and stored into the database. Now we also have database based corpus in which we have
tables for three sections that are celebrity, normal and promotional. The bio words stored
in the database are then matched with corpus tables and frequency of token match is
stored in a final table.

7. Unique commentators in posts: It is a very essential factor test. In this, we fetch the list of
commentators of X no of posts. X is a integer value given by the user. By default, the
value is set to 12, 24, 36. That means the system fetches the commentators of first 12
posts, 24 posts and 36 posts. Now we find the intersection and get the common
commentators. It high value indicates the presence of normal account and low value
indicates the presence of a promotional or celebrity account.

8. Frequency of different dates of posts with respect to number of posts: This factor fetches
the number of posts and the dates on which the posts were posted. Then we calculate the
ratio of posts to date. Its closeness to unity shows the presence of normal account and
value greater than 20 shows the presence of promotional and celebrity account.
