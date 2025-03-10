ExpenseTrackerJava
Just a simple beginner expense tracker(V1.0) that is made on java ;

Expense Tracker (Java Console Application)

📌 Overview
This is a simple **Expense Tracker** program written in Java. It allows users to:
- **Add** expenses
- **Update** expenses
- **View** expenses within a date range
- **Delete** expenses
- **Set a budget**

Data is stored in a CSV file (`data.csv`). In future updates, a database integration might be added. 😊

---

🛠 Installation & Setup
1️⃣ Install Java (JDK 17 or later)
- Download & Install from [Oracle JDK](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html) or use `OpenJDK`.
- Verify installation by running:
  ```bash
  java -version
  ```

2️⃣ Clone the Repository
```bash
git clone https://github.com/YOUR_GITHUB_USERNAME/Expense-Tracker.git
cd Expense-Tracker
```

3️⃣ Compile & Run the Program
Option 1: Using Terminal/Command Prompt
```bash
javac Main.java
java main.Main
```

Option 2: Using an IDE (Easier)
✅ Open the project in **Eclipse, IntelliJ, or VS Code**  
✅ Click **Run ▶** on `Main.java`

---

🚀 How to Create a Runnable JAR File (Optional)
This allows users to double-click to run the program.

1️⃣ Compile:
```bash
javac Main.java
```
2️⃣ Create JAR:
```bash
jar cfe ExpenseTracker.jar main.Main Main.class
```
3️⃣ Run the JAR:
```bash
java -jar ExpenseTracker.jar
```
Now users can **double-click** `ExpenseTracker.jar` to launch the program! 🎉

---

📝 Features & Future Plans
- ✅ Basic expense tracking using CSV
- 🔜 Implement **Object-Oriented Programming (OOP) refactor**
- 🔜 Integrate with **MySQL database** instead of CSV





