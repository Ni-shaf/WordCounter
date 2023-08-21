# WordCounter

It is a microservice to expose the “Word Counter” functionality

It has the following features:
1. PUT API that allows you to add one or more words.
2. GET API that returns the count of how many times a given word was added to the word counter.
3. It doesn't allow addition of words with non-alphabetic characters.
4. It treats same words written in different languages as the same word by assuming that translation of
words is done via external class Translator that will have a translate method accepting word as an argument and returing English name for it.
5. No persistence or in-memory DB used.


### APIs

**PUT**
`Http://localhost:8080/word_counter/words/add`
* Sample Request body
  * ["word1","word2", "WORD1","word3"]
* Sample Response
  * 200 OK

**GET**
`Http://localhost:8080/word_counter/words/getCount?word=word1`
* Sample Response
  * 2
