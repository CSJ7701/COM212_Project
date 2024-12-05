# COM212 Project 
> Thursday, Dec 5th.

This document serves as a user manual, describing how the program operates and how someone might interact with or use its built-in functions.

## Project Members
- Christian
- Claire
- Antonio
- Quentin

## Directory Path
`.../COM212_Project/src` -> (from there, refer to main)

## Program Overview
Upon running `main.java` in `src`, the user will be greeted with a command-line interface and prompted with an initial start-up "Welcome" scene, accompanied by the various functions that our program has to offer. 

## Menu Buttons
Below is a list of menu "buttons". Each button calls a function that interacts with the database, whether it be data manipulation, generic searching, or the like:

1. **Add Idea**.
2. **Add Student**.
3. **Search Students**.
4. **Search Ideas**.
5. **Get Best Idea**.
6. **Exit**.

## How to Trigger a Function Call from the Menu Display
Each function is tied to a numeric value to assist the user in selecting the desired functionality. The user may enter an integer value (corresponding to the desired action) in the blank space following the green `"Enter your choice: "` prompt, which is displayed at the bottom of the menu.

## What happens after you select your desired functionality?
Entering a number 1-5 (6 simply serves as a means of terminating the program), a new menu scene will be displayed according to the given selection:

1. User is greeted with a `"Create a new idea"` display, and asked to `"Please enter details: "` below. Above the blue dividing line the *Student's SSN*, *Description*, and *Rating* are all "Not Set" by default, so the user may enter the specific information at the bottom of the menu display. Enter the `Student's SSN` as an integer, the `Description` using alphanumeric characters, and the `Rating` as another integer from 0-100.
If there were any issues with the previously inputted information, you may `"Press enter to try again."`.

2. User is greeted with a `"Create a new student"` display, and asked to `"Please enter details: "` below. Above the blue dividing line the *Name*, *Email*, *SSN*, and *Student ID* are all "Not Set" by default, so the user may enter the specific information at the bottom of the menu display. Enter `Name` using alphanumeric characters, `Email` using alphanumeric characters - but it must conform to standard email formatting, Student's `SSN` as an integer, and lastly `Student ID` as an integer as well.
After inputting the new information, the user will be asked to verify if the info they inputted is correct by entering `y` for "yes" and `n` for "no". Entering "no" just restarts the process while entering "yes" will bring you back to the initial main menu display and will have thereby successfully Added a new student to the database.

3. User is greeted with a `"Search for a student"` display and informed that one `"Can search using SSN or Student ID."`. Similar to the initial main menu display, there are three new buttons numbered accordingly: `Search with SSN`, `Search with ID`, and `Exit`. Entering 1 will prompt the user to enter the last 4 digits of the Student's SSN that user wishes to look up. Entering 2 will similarly prompt the user to enter the Student's ID number.

4. User is greeted with a `"Search Ideas by ID"` display and informed that one may `"Enter an ID to find the corresponding idea."`. Given the prompt below the blue dividing line, "Enter an Idea ID (or 'exit' to get back): ", the user is expected to input an Idea's numeric ID as an integer value to proceed with the look up. Simply typing `exit` will return to the initial main menu display.

5. Assuming there is preexisting information in the database, user is greeted with a `"Idea Details"` display for the best idea where the given *ID*, *Submitter SSN*, *Description*, and *Rating* for this best idea are all displayed to be viewed by the user. Additionally there are new buttons below which allow the user to `1. Edit Idea`, `2. Delete Idea`, or `3. Quit`. Entering "1" will allow the user to edit this idea by first allowing the user to enter a new description, then a new rating - leaving blank and pressing enter will simply leave that given element as it was prior to the edit. Entering "2" will allow the user to delete this idea and will be prompted for confirmation "yes/no". The user will be returned to the initial main menu display upon a successful delete.

6. Exit = Program termination.