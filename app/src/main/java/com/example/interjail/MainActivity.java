package com.example.interjail;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView dateTextView;
    private TextView timeTextView;
    private TextView editJourneysTextView;
    private ImageView qrCodeImageView;
    private ImageView watermarkImageView;
    private Handler timeHandler = new Handler();
    private Runnable timeRunnable;
    
    private static final int WHITE = 0xFFFFFFFF;
    private static final int BLACK = 0xFF000000;
    private static final int QR_CODE_WIDTH = 1000;
    private static final int QR_CODE_HEIGHT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find views
        dateTextView = findViewById(R.id.textViewDate);
        timeTextView = findViewById(R.id.textViewTime);
        editJourneysTextView = findViewById(R.id.textViewEditJourneys);
        qrCodeImageView = findViewById(R.id.imageViewQrCode);
        watermarkImageView = findViewById(R.id.imageViewWatermark);

        // Set current date
        updateCurrentDate();
        
        // Start updating time
        startTimeUpdates();

        // Generate QR code
        generateQRCode();
        
        // Create watermark text
        createWatermarkImage();
        
        // Set up click listeners
        setupClickListeners();
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Stop time updates when activity is destroyed
        timeHandler.removeCallbacks(timeRunnable);
    }

    /**
     * Updates the toolbar with the current date in format "Today, d MMMM"
     */
    private void updateCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMMM", Locale.US);
        String formattedDate = dateFormat.format(new Date());
        dateTextView.setText("Today, " + formattedDate);
    }
    
    /**
     * Starts periodic updates of the current time
     */
    private void startTimeUpdates() {
        timeRunnable = new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.US);
                String formattedTime = timeFormat.format(new Date());
                timeTextView.setText(formattedTime);
                timeHandler.postDelayed(this, 1000); // Update every second
            }
        };
        timeHandler.post(timeRunnable);
    }
    
    /**
     * Sets up click listeners for interactive elements
     */
    private void setupClickListeners() {
        // Edit journeys button click listener
        editJourneysTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement your action here for editing journeys
                // For example: open a new activity for editing journeys
            }
        });
    }
    
    /**
     * Creates a watermark image with pass information
     */
    private void createWatermarkImage() {
        // Create a bitmap with text watermark
        Bitmap watermarkBitmap = Bitmap.createBitmap(1000, 300, Bitmap.Config.ARGB_8888);
        android.graphics.Canvas canvas = new android.graphics.Canvas(watermarkBitmap);
        canvas.drawColor(WHITE);
        
        android.graphics.Paint paint = new android.graphics.Paint();
        paint.setColor(0xFFCCCCCC);
        paint.setTextSize(24f);
        paint.setAntiAlias(true);
        
        String[] watermarkLines = {
            "EURAIL GLOBAL PASS / YQ6F5A / 1ST CLASS / EURAIL GLOBAL PASS / YQ6F5A",
            "P. PETROVA / 11-02 / YQ6F5A / 1ST CLASS / EURAIL GLOBAL PASS / 04-08-2020",
            "EURAIL GLOBAL PASS / YQ6F5A / 1ST CLASS / EURAIL GLOBAL PASS / YQ6F5A",
            "P. PETROVA / 11-02 / YQ6F5A / 1ST CLASS / EURAIL GLOBAL PASS / 04-08-2020"
        };
        
        float y = 30;
        for (String line : watermarkLines) {
            canvas.drawText(line, 20, y, paint);
            y += 40;
        }
        
        watermarkImageView.setImageBitmap(watermarkBitmap);
    }
    
    /**
     * Generates a QR code containing pass information and displays it in the ImageView
     */
    private void generateQRCode() {
        try {
            // Create a more complex QR code content with additional data to make it more dense
            StringBuilder complexContent = new StringBuilder();
            
            // Basic pass information
            complexContent.append("EURAIL GLOBAL PASS\n");
            complexContent.append("Name: P. Petrova\n");
            complexContent.append("DOB: 11-02-1982\n");
            complexContent.append("Pass Number: YQ6F5A\n");
            complexContent.append("Class: 1ST CLASS\n");
            complexContent.append("Valid on: 04-08-2022\n");
            complexContent.append("Passport/ID: 000643542870RTTR\n\n");
            
            // Add more complex data to make the QR code more dense
            complexContent.append("Issue Date: 01-08-2020\n");
            complexContent.append("Expiration Date: 31-08-2022\n");
            complexContent.append("Pass Type: GLOBAL\n");
            complexContent.append("Countries: ALL\n");
            complexContent.append("Issuing Office: EURAIL-ONLINE-" + generateRandomCode(8) + "\n");
            complexContent.append("Transaction ID: TXN-" + generateRandomCode(12) + "\n");
            complexContent.append("Verification: VRF-" + generateRandomCode(16) + "\n");
            
            // Add travel history to make QR code even more complex
            complexContent.append("Travel History:\n");
            complexContent.append("- Amsterdam (NL) -> Brussels (BE) - 01-08-2022\n");
            complexContent.append("- Brussels (BE) -> Paris (FR) - 02-08-2022\n");
            complexContent.append("- Paris (FR) -> Lyon (FR) - 03-08-2022\n");
            
            // Add security hash
            complexContent.append("Security Hash: " + generateRandomCode(32) + "\n");
            complexContent.append("Validation: " + generateRandomCode(24) + "\n");
            
            // Generate the QR code bitmap with higher error correction level for more complex appearance
            Bitmap qrCodeBitmap = createComplexQRCode(complexContent.toString());
            
            // Display the QR code
            qrCodeImageView.setImageBitmap(qrCodeBitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Generates a random alphanumeric code of specified length
     * 
     * @param length The length of the code to generate
     * @return Random alphanumeric code
     */
    private String generateRandomCode(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        
        for (int i = 0; i < length; i++) {
            result.append(characters.charAt(random.nextInt(characters.length())));
        }
        
        return result.toString();
    }
    
    /**
     * Creates a more complex QR code bitmap from the given content
     * 
     * @param content The content to encode in the QR code
     * @return Bitmap containing the QR code
     * @throws WriterException If there's an error generating the QR code
     */
    private Bitmap createComplexQRCode(String content) throws WriterException {
        // Set hints to create a more complex QR code
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H); // Higher error correction = more complex pattern
        hints.put(EncodeHintType.MARGIN, 1); // Smaller margin to fit more data
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, QR_CODE_WIDTH, QR_CODE_HEIGHT, hints);
        
        // Convert BitMatrix to Bitmap
        Bitmap bitmap = Bitmap.createBitmap(QR_CODE_WIDTH, QR_CODE_HEIGHT, Bitmap.Config.ARGB_8888);
        for (int x = 0; x < QR_CODE_WIDTH; x++) {
            for (int y = 0; y < QR_CODE_HEIGHT; y++) {
                bitmap.setPixel(x, y, bitMatrix.get(x, y) ? BLACK : WHITE);
            }
        }
        
        return bitmap;
    }
}
