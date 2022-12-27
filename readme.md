**What are design patters?**
 
In software engineering, a software design pattern is a general, reusable solution to a commonly occurring problem within a given context in software design.
It is not a finished design that can be transformed directly into source or machine code. Rather, it is a description or template for how to solve a problem that can be used in many different situations.
Design patterns are formalized best practices that the programmer can use to solve common problems when designing an application or system.
more about design patterns here:https://en.wikipedia.org/wiki/Software_design_pattern

**What is the Observer design pattern?**
 
In software design and engineering, the observer pattern is a software design pattern in which an object, named the subject,
maintains a list of its dependents, called observers, and notifies them automatically of any state changes, usually by calling one of their methods.
more about observer here:https://en.wikipedia.org/wiki/Observer_pattern#:~:text=In%20software%20design%20and%20engineering,calling%20one%20of%20their%20methods.

![image](https://user-images.githubusercontent.com/28539893/209655455-32e20343-f897-4590-9876-d66202e41ba9.png)

**Lets go over our project a bit:**
 
We built a class called GroupAdmin that is our Observable. GroupAdmin holds a list of members and String of type UndoableStringBuilder.
It has the following methods :
 *register
 *unregister
 *append
 *insert
 *delete
 *undo
If you would like to know more about the methods, we documented them with javadoc.

Everytime GroupAdmin updates its String it updates all of it's members about the change.
The GroupAdmin can decide to register or unregister members.Any member that gets unregistered will no longer recieve updates.



