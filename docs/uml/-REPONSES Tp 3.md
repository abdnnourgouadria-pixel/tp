
## 5) Réponses ta3 les questions


(a) Layout
PlantUML (via Graphviz) places the elements 
automatically, so the diagram is clean and aligned 
without effort, but not always optimal: some links
cross or make long detours (e.g. the Mail server link). We cannot drag shapes; we can only
influence the layout through the code (declaration order, "left to right direction", notes), and a small change can move the whole diagram.

(b) Error detection
The tool detects syntax errors (a misspelled
keyword, a missing "endif") and shows a message with 
the line number. It does not detect modelling errors: use-cases-reversed.
puml compiles without any warning even though the <<extend>> arrow points the wrong way, so the modeller must check the meaning.

(c) Advantages and drawbacks of PlantUML
Advantages: it is free, quick to write, plain 
text that can be versioned and compared with diff, and the automatic layout saves time. Drawbacks: the syntax must be learned, there is little control over the final layout, large diagrams
become hard to read, and it depends 
on external tools (Java, Graphviz).

(d) Comparison with a graphical tool
A graphical tool (draw.io, StarUML) gives full control
over positions and appearance and immediate visual
feedback, but the work is manual and its files are hard to compare and merge. PlantUML is better when models change often or are shared in a team;
a graphical tool is better
for a polished presentation.

(e) Keeping models with the code
Models stored in the repository are versioned with the code, so each software version
has its matching model. They are modified and reviewed in the same commit as the code, which keeps the documentation up to date, and any developer
can find and regenerate them.
