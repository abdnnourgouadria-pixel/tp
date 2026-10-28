# Git commands for TP 2 (run one at a time)

Replace the name and email with your own details.

## 1. Configure Git
git --version
git config --global user.name "abdou"
git config --global user.email "abdou@gmail.com"
git config --global init.defaultBranch main

## 2. First commit (run inside this project folder)
git init
git status
git add .
git status
git commit -m "Create the Maven project"

## 3. Suggested small commits
After you have made each stage's changes, run:
git status
git diff
git add .
git diff --staged
git commit -m "Add book and copy classes"

Use a new meaningful message for each later stage. Do not run several commit commands in a row without making changes between them.

## 4. Branch and merge exercise
The final Java files in this ZIP already include searchByTitle so the app is ready to use.
For an exact branch exercise, first commit the base project without the searchByTitle method and its search test, then run:

git switch -c feature/search

Add searchByTitle to Library.java, add the search test, and change README.md first line to:
# Library management – TP 2 (with search)

git add .
git commit -m "Add book title search"
git switch main

Change README.md first line to:
# Library management application – TP 2

git add README.md
git commit -m "Update project title"
git merge feature/search

If README.md has a conflict, keep one correct title, remove all conflict markers, then run:
git add README.md
git commit -m "Merge search feature"

git log --oneline --graph --all
git branch -d feature/search

## 5. GitHub remote (optional)
Create an empty repository named tp2-library on GitHub. Replace the URL below with your actual repository URL:
git remote add origin YOUR_REPOSITORY_URL
git push -u origin main
git tag -a v1.0 -m "First version"
git push origin v1.0

Never paste your GitHub password or access token into a public file.
