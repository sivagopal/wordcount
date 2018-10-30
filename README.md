# WordCount Service

###This is an application named "WordCounter", with the following two distinct methods:
<ul>
<li>	method that allows you to add words (WordCountService#addWord).</li>
<li>	method that returns the count of how many times a given word was added to the word counter
(WordCountService#countWord)</li>
</ul>

##having the following requirements:

<ul>
<li>	should NOT allow addition of words with non-alphabetic characters.</li>
<li>should treat same words written in different languages as the same word, for example if
adding "flower", "flor" (Spanish word for flower) and "blume" (German word for flower) the counting method should 
return 3.</li>
</ul>