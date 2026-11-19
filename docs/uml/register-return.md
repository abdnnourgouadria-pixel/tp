# UC-07 – Register a return

| Field | Description |
|---|---|
| **ID / Name** | UC-07 – Register a return |
| **Goal** | Record the return of a borrowed copy and close the loan |
| **Primary actor** | Librarian |
| **Secondary actor** | Mail server (notification e-mail) |
| **Scope** | Library system |
| **Preconditions** | The librarian is authenticated. The copy is currently on loan to a member. |
| **Trigger** | A member brings a copy back to the desk. |
| **Postconditions (success)** | The loan is closed, the copy is available again (or kept aside if reserved). |
| **Postconditions (failure)** | The loan stays open; nothing is changed. |

## Main scenario
1. The member gives the copy to the librarian.
2. The librarian scans the copy.
3. The system identifies the open loan of this copy.
4. The system closes the loan and records the return date.
5. The system checks the reservations: none.
6. The system sets the copy "available".
7. The system confirms the return. End of use case.

## Alternative scenarios / exceptions
- **2a. Unknown copy / barcode unreadable**: the system shows an error; the librarian types the code by hand or stops the use case.
- **3a. No open loan for this copy**: the system shows "copy not on loan"; the use case ends.
- **4a. Late return** (*extension point: late return*): after step 4, the use case **Suspend the member** is activated (condition `{returned late}`); the member is suspended for a period; then the scenario continues at step 5.
- **5a. Copy reserved by another member**: the system keeps the copy aside (status "reserved"), sends a notification to the member who reserved it through the Mail server, then ends at step 7.

## Included / extending use cases
- Extended by: **Suspend the member** (extension point *late return*).

## Business rules
- A late return = return date later than the due date.
- A reserved copy is kept aside for the first member in the reservation queue.
