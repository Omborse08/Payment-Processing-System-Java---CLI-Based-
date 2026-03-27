# 💳 Payment Processing System (Java - CLI Based)

A CLI-based backend system that simulates a **real-world payment engine** with concepts like:

* Ledger-based balance tracking
* Idempotent transactions
* Transaction state management
* Failure recovery
* Clean separation of concerns (LLD)

---

# 🚀 Features

* ✅ Create users with initial balance
* 💸 Send money between users
* 📊 Check balance (computed via ledger)
* 📜 View transaction history
* 🔁 Recover incomplete/failed transactions
* 🔒 Lock simulation (for concurrency readiness)
* 🧠 Idempotency support (no duplicate transactions)

---

# 🧠 Core Concepts Used

### 1. Ledger System

Instead of storing balance directly, all transactions are recorded as:

* CREDIT → money added
* DEBIT → money deducted

Balance is calculated dynamically:

```
Balance = Total Credits - Total Debits
```

---

### 2. Payment State Machine

Each payment goes through states:

```
INITIATED → DEBIT_DONE → CREDIT_DONE → SUCCESS
```

Failure path:

```
DEBIT_DONE → FAILED (via recovery)
```

---

### 3. Idempotency

Each transaction has a unique `paymentId`.

* Same ID = ignored (prevents duplicate charges)

---

### 4. Recovery System

If system crashes after debit but before credit:

* System detects incomplete state (`DEBIT_DONE`)
* Rolls back transaction safely

---

# 🏗️ Project Structure

```
Main_Cli.java          → CLI entry point
User                  → User model
UserRepository        → Stores users

Payment               → Payment entity (state machine)
PaymentRepository     → Stores payments

LedgerEntry           → Single transaction record
LedgerService         → Handles all money logic

PaymentService        → Core business logic (brain)

LockAccount           → Lock simulation
RecoverClass          → Recovery logic
```

---

# 🔗 System Flow

### SEND MONEY Flow

```
Main → PaymentService

PaymentService:
  → Check user exists
  → Check duplicate paymentId
  → Save payment
  → Lock users
  → Check balance
  → Debit sender
  → Update status
  → Credit receiver
  → Update status
  → Unlock users
```

---

### RECOVERY Flow

```
RecoveryManager:
  → Scan all payments
  → If status == DEBIT_DONE
      → rollback (credit sender)
      → mark FAILED
```

---

# 🖥️ CLI Usage

```
1. CreateUser
2. SendMoney
3. CheckBalance
4. History
5. Recover
6. Exit
```

---

## 🟢 Create User

```
Enter Name: A
Enter Initial Balance: 1000
```

---

## 🔵 Send Money

```
Enter Sender ID: 101
Enter Receiver ID: 102
Enter Amount: 200
```

---

## 🟡 Check Balance

```
Enter User ID: 101
```

---

## 📜 History

Shows all ledger entries for a user.

---

## 🔁 Recover

Fixes incomplete transactions.

---

# ⚙️ Design Principles

* Single Responsibility Principle
* Separation of Concerns
* Immutable Ledger (append-only)
* State-driven transactions
* Constructor-based dependency injection

---

# ❗ Key Engineering Decisions

| Problem                 | Solution                |
| ----------------------- | ----------------------- |
| Balance inconsistency   | Ledger-based system     |
| Duplicate requests      | Idempotency (paymentId) |
| Crash handling          | State + Recovery        |
| Race condition (future) | Lock abstraction        |

---

# ⚠️ Limitations

* No real multithreading (lock is simulated)
* In-memory storage (no database)
* No persistence after restart

---

# 🔮 Future Improvements

* Add real multithreading with `ReentrantLock`
* Use database (MySQL/PostgreSQL)
* Add REST API (Spring Boot)
* Implement distributed locks (Redis)
* Event-driven architecture (Kafka)

---

# 🧪 Sample Scenario

```
A → ₹1000
B → ₹500

SEND A → B ₹200

Result:
A → ₹800
B → ₹700
```

---

# 🧠 What This Project Demonstrates

* Real backend system thinking
* Transaction safety
* Failure handling
* Clean low-level design (LLD)

---

# 📌 Author

Built as a backend engineering practice project focused on **system design fundamentals and correctness over simplicity**.

---
