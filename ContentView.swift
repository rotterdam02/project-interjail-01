import SwiftUI

struct ContentView: View {
    // Computed property to get the formatted current date
    var currentDate: String {
        let formatter = DateFormatter()
        formatter.locale = Locale(identifier: "en_US")
        // Set your desired date format, for example "d MMMM" for "4 August"
        formatter.dateFormat = "d MMMM"
        return formatter.string(from: Date())
    }
    
    var body: some View {
        ZStack(alignment: .top) {
            // Background color for the entire screen
            Color.white
                .edgesIgnoringSafeArea(.all)
            
            VStack(spacing: 0) {
                // Top bar with "Today, <current date>" centered
                HStack {
                    Spacer()
                    Text("Today, \(currentDate)")
                        .font(.system(size: 17, weight: .semibold))
                        .foregroundColor(.white)
                    Spacer()
                }
                .padding(.horizontal)
                .padding(.top, 16)
                .padding(.bottom, 12)
                .background(Color("NavBarColor"))
                
                // SCROLLABLE CONTENT (Pass Card)
                ScrollView {
                    VStack(alignment: .leading, spacing: 16) {
                        
                        // QR CODE (Placeholder image from Assets, named "q")
                        Image("q")
                            .resizable()
                            .scaledToFit()
                            .frame(width: 350, height: 350)
                            .frame(maxWidth: .infinity, alignment: .center)
                            .padding(.top, 16)
                        
                        // PASS NUMBER
                        Text("PASS NUMBER YQ6F5A")
                            .font(.system(size: 14))
                            .foregroundColor(.gray)
                            .frame(maxWidth: .infinity, alignment: .center)
                        
                        // PASS DETAILS
                        VStack(alignment: .leading, spacing: 6) {
                            Text("Eurail Global Pass")
                                .font(.system(size: 18, weight: .semibold))
                            
                            Text("1st Class Adult")
                                .font(.system(size: 16))
                                .foregroundColor(.secondary)
                            
                            Text("Valid on 04-08-2022")
                                .font(.system(size: 14))
                                .foregroundColor(.secondary)
                        }
                        
                        Divider()
                        
                        // PERSONAL INFO
                        VStack(alignment: .leading, spacing: 4) {
                            Text("Name: P. Petrova")
                            Text("Date of birth: 11-02-1982")
                            Text("Residence: United States")
                            Text("Passport / ID: 000463452870RTR")
                            Text("Activated on 01-08-2022, 10:30")
                        }
                        .font(.system(size: 14))
                        
                        // EDIT JOURNEYS BUTTON (right-aligned)
                        HStack {
                            Spacer()
                            Button(action: {
                                // Your action here
                            }) {
                                Text("Edit journeys")
                                    .font(.system(size: 14))
                                    .foregroundColor(.blue)
                                    .underline()
                            }
                        }
                    }
                    .padding(16)
                    .background(Color.white)
                    .cornerRadius(12)
                    .shadow(color: Color.black.opacity(0.15), radius: 5, x: 0, y: 3)
                    .padding(.horizontal, 16)
                    .padding(.top, 16)
                }
                
                Spacer() // Pushes content upward if needed
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
            .previewDevice("iPhone 14 Pro")
    }
}
