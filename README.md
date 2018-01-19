# streams-demo

Java streams are awesome, but as with all things they should be used in moderation.  Just because you _can_ do a thing doesn't mean you _should_ do a thing.

A stream is a sequence of elements on which operations can be performed.
* operations can be performed in sequence (stream) or in parallel (parallelStream)
* operations can be chained
* an operation is either intermediate (filter, map, sorted) or terminal (forEach). Nothing happens with the intermediate operations _unless_ the chain includes a terminal operation
    * an intermediate operation returns a stream
    * a terminal operation returns a result (list, collection, bool, optional, etc) or void
* most stream expressions take a lambda expression to define the operations they perform

A lambda Expression is a function which can be created without belonging to any class.
* can be passed around as if it was an object and executed on demand
* practically speaking, it's a nice way to shortcut A LOT of the verbosity that Java typically demands

Streams and Lambda bring functional programming into Java.  If you like jargon, google Monad.  