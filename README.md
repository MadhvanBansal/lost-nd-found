# 🔍 Lost & Found System - Professional Edition

Welcome to the **Lost & Found System**, a desktop application designed to bridge the gap between people who have lost their precious belongings and the kind souls who have found them. 

Have you ever lost your keys, wallet, or phone, and simply didn't know where to turn? Or perhaps you've stumbled upon a misplaced item and wanted to ensure it finds its way back to its rightful owner? This Java-based application provides a seamless, centralized platform to make that happen!

## ✨ What Does It Do?

Our system makes it incredibly easy to report and track items:
- **🔐 Secure Login & Registration:** personalized accounts ensure safety and accountability.
- **📊 Interactive Dashboard:** Get a bird's-eye view of your community's active lost & found items with real-time stats.
- **📢 Report Lost Items:** Quickly list what you've lost, where, and when so others can keep an eye out.
- **🤝 Report Found Items:** Easily log items you’ve discovered to help them find their way home.
- **🔎 Smart Database Search:** A clean, easy-to-use search capability to instantly connect matching items.

## 🛠️ How It Works (Under the Hood)

Built with love and **Java Swing**, the application uses a modern "Single-Window" architecture. Instead of annoying pop-up windows cluttering your screen, we use a sleek `CardLayout` that transitions smoothly between different views (like flipping pages in a book!). 

The project structure is professionally separated into responsibilities:
- `model/`: The blueprints for our data (like what an `Item` actually is).
- `services/`: The brains of the operation, handling logic like user authentication and counting missing/found statistics.
- `ui/`: The beautiful face of the app! Powered by custom-styled buttons, vibrant color palettes, and modern typography to keep the user experience delightful.

## 🚀 Getting Started

If you want to run this project on your own machine, follow these simple steps:

1. **Clone the repository:**
   ```bash
   git clone https://github.com/MadhvanBansal/lost-nd-found.git
   ```
2. **Navigate to the setup directory:**
   ```bash
   cd lost-nd-found
   ```
3. **Compile the Java files:**
   ```bash
   javac scr/main/MainApp.java
   ```
4. **Run the Application:**
   ```bash
   java scr.main.MainApp
   ```

## 🎨 A Note on Design

We believe utility software shouldn't look boring! The interface has been crafted with custom hexadecimal colors and clean padding to provide a pleasant and premium user experience out of the box. No external design libraries needed—just pure Java UI magic.

---
*Created to keep the community connected and belongings safe.* 💛
