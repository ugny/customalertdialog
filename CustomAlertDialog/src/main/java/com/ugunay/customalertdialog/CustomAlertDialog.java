package com.ugunay.customalertdialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * * Created by Uğur Günay on 20/12/2020.
 * <p>
 * Bu sınıf standart alert dialoglardan sıkılmış kişilere özelleştirilebilir alert custom_alert_dialog
 * kullanımı sunar. Bu sınıf ile
 * * Başlığın metin rengi ve başlık alanının arka plan rengi,
 * * Mesajın metin rengi ve mesaj alanının arka plan rengi,
 * * Pozitif ve negatif butonların metin ve arka plan renkleri
 * özelleştirilebilir. Ayrıca pozitif ve negatif butonlara icon ataması da yapılabilir.
 * Alert custom_alert_dialog penceresinin ikonuna ait icon tint color da özelleştirilebilir.
 * <p>
 * Yazılımcı isterse geliştirdiği uygulamanın launcher activity'sinde bu renk değişkenlerine
 * bir kereliğine atama yapabilir. Static olan değişkenler sayesinde projenin her yerinde
 * aynı tasarım geçerli olucaktır. Yazılımcı renk değişkenlerine bir atama yapmazsa değişkenlerin
 * varsayılan renkleri geçerli olucaktır. Yazılımcı isterse tanımladığı alert dialoga özel
 * bu renkleri özelleştirebilir. Bu özel alert custom_alert_dialog tanımlamasındaki atanan renklerden
 * varsayılan renkler ya da yazılımcının en başta belirlediği renkler etkilenmez.
 * <p>
 * Renk değişkenlerine atama işlemi eğer color dosyasından yapılacaksa
 * context.getResources().getColor(R.color.your_color) yöntemi kullanılmalıdır.
 * <p>
 * En çok kullanılan alert custom_alert_dialog tasarımlarını hazır olarak kullanıma sunar. Bu sınıf sayesinde
 * * Başlıksız sadece mesaj ve tamam butonu bulunan bir alert custom_alert_dialog,
 * * İkonsuz, başlığı, mesajı ve tamam butonu bulunan bir alert custom_alert_dialog,
 * * Bilgilendirme, başarı ve hata durumlarına ait alert dialoglar,
 * (Bu alert dialoglar sabit ikonlara ve tamam butonuna sahiptir. Başlık ve mesaj bilgisini dışarıdan alır.)
 * * Evet-Hayır butonlarına sahip uyarı yapan bir alert custom_alert_dialog, (Evet butonuna ait OnClickListener olayını dışarıdan alır.)
 * gibi alert dialoglar hazır olarak kullanılabilir.
 * <p>
 * Farklı bir tasarımda alert custom_alert_dialog kullanılmak istenirse büyük ikonlu bir alert custom_alert_dialog kullanımı
 * sunan setDialogWithLargeIcon() fonksiyonu tercih edilebilir. Uygulama izinleriyle ilgili
 * kullanıcıya bilgi vermek için bu büyük ikonlu alert custom_alert_dialog tasarımının yardımıyla oluşturulmuş
 * showPermissionDialog() fonksiyonu kullanılabilir.
 * <p>
 * CharSequence dizilerini gösteren, dizi üzerinde tekli veya çoklu seçim işlemi yapılmasını sağlayan
 * alert dialogları kullanıma sunar.
 */
public class CustomAlertDialog {

    /**
     * Factory method.
     *
     * @param context context
     * @return a new instance of this class.
     */
    public static CustomAlertDialog newInstance(Context context) {
        return new CustomAlertDialog(context);
    }

    private Context context;
    private Dialog dialog;
    private TextView txtYes = null;
    private TextView txtNo = null;


    /**
     * Constructur.
     *
     * @param context context.
     */
    public CustomAlertDialog(Context context) {
        this.context = context;
        setDialog();
    }

    /**
     * Dialog nesnesinin başlangıç değerlerni ve görünümünü set eder.
     */
    private void setDialog() {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom_alert_dialog);
        Window window = dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawableResource(R.color.custom_alert_dialog_color_transparent);
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            window.getAttributes().windowAnimations = R.style.custom_alert_dialog_animation;
        }
    }

    //---------------------------------Prepared Dialogs---------------------------------------------

    /**
     * Bilgilendirme ile ilgili hazır bir alert custom_alert_dialog penceresi gösterir.
     *
     * @param title   custom_alert_dialog başlığı.
     * @param message custom_alert_dialog mesajı.
     */
    public void showInfoDialog(String title, String message) {
        showPreparedDialog(context.getResources().getColor(R.color.custom_alert_dialog_color_info),
                R.drawable.ic_info_for_custom_alert_dialog, title, message);
    }

    /**
     * Başarılı bir işlem sonucuna ait hazır bir alert custom_alert_dialog penceresi gösterir.
     *
     * @param title   custom_alert_dialog başlığı.
     * @param message custom_alert_dialog mesajı.
     */
    public void showSuccessDialog(String title, String message) {
        showPreparedDialog(context.getResources().getColor(R.color.custom_alert_dialog_color_success),
                R.drawable.ic_check_circle_for_custom_alert_dialog, title, message);
    }

    /**
     * Hatalı bir işlem sonucuna ait hazır bir alert custom_alert_dialog penceresi gösterir.
     *
     * @param title   custom_alert_dialog başlığı.
     * @param message custom_alert_dialog mesajı.
     */
    public void showErrorDialog(String title, String message) {
        showPreparedDialog(context.getResources().getColor(R.color.custom_alert_dialog_color_error),
                R.drawable.ic_error_for_custom_alert_dialog, title, message);
    }

    /**
     * Hazır alert custom_alert_dialog pencereleri gösterir.
     *
     * @param titleAndButtonBackgroundColor custom_alert_dialog başlığı ve butonuna ait arka plan rengi.
     * @param iconResId                     custom_alert_dialog başlığına ait icon.
     * @param title                         custom_alert_dialog başlığı.
     * @param message                       custom_alert_dialog mesajı.
     */
    private void showPreparedDialog(final int titleAndButtonBackgroundColor, final int iconResId,
                                    String title, String message) {
        setIcon(iconResId);
        setTitle(title, titleAndButtonBackgroundColor, Color.WHITE);
        setMessage(message);
        setOkButton(titleAndButtonBackgroundColor);
        show();
    }

    /**
     * Hazır alert custom_alert_dialog pencrelerin (bilgilendirme, başarı ve hata) tamam butonunu set eder.
     *
     * @param backgroundColor tamam butonuna ait arka plan rengi.
     */
    private void setOkButton(final int backgroundColor) {
        TextView txtYes = dialog.findViewById(R.id.txtYes);
        txtYes.setVisibility(View.VISIBLE);
        txtYes.setBackgroundColor(backgroundColor);
        txtYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    /**
     * Uyarı durumuna ait hazır alert custom_alert_dialog penceresi gösterir.
     *
     * @param title                 custom_alert_dialog başlığı.
     * @param message               custom_alert_dialog mesajı.
     * @param btnYesOnClickListener custom_alert_dialog penceresindeki Yes butonuna ait OnClickListener olayı.
     */
    public void showWarningDialog(String title, String message, final OnClickListener btnYesOnClickListener) {
        int colorWarning = context.getResources().getColor(R.color.custom_alert_dialog_color_warning);
        setIcon(R.drawable.ic_warning_for_custom_alert_dialog);
        setTitle(title, colorWarning, Color.WHITE);
        setMessage(message);
        setPositiveButton(context.getString(R.string.yes), btnYesOnClickListener, colorWarning, Color.WHITE);
        setNegativeButton(context.getString(R.string.no),
                new OnClickListener() {
                    @Override
                    public void onClick(CustomAlertDialog dialog) {
                        dialog.dismiss();
                    }
                },
                negativeButtonBackgroundColor, Color.WHITE);
        show();
    }
//--------------------------------------------------------------------------------------------------

    //-----------------------Prepared Dialogs With Large Icon---------------------------------------
    // Büyük ikonlu hazır alert dialoglar başlık bilgisi bulundurmaz.

    /**
     * Bilgilendirme ile ilgili büyük ikonlu hazır bir alert custom_alert_dialog penceresi gösterir.
     *
     * @param message custom_alert_dialog mesajı.
     */
    public void showInfoDialog(String message) {
        showPreparedDialogWithLargeIcon(R.drawable.ic_info_for_custom_alert_dialog, message,
                context.getResources().getColor(R.color.custom_alert_dialog_color_info));
    }

    /**
     * Başarılı bir işlem sonucuna ait büyük ikonlu hazır bir alert custom_alert_dialog penceresi gösterir.
     *
     * @param message custom_alert_dialog mesajı.
     */
    public void showSuccessDialog(String message) {
        showPreparedDialogWithLargeIcon(R.drawable.ic_check_circle_for_custom_alert_dialog, message,
                context.getResources().getColor(R.color.custom_alert_dialog_color_success));
    }

    /**
     * Hatalı bir işlem sonucuna ait büyük ikonlu hazır bir alert custom_alert_dialog penceresi gösterir.
     *
     * @param message custom_alert_dialog mesajı.
     */
    public void showErrorDialog(String message) {
        showPreparedDialogWithLargeIcon(R.drawable.ic_error_for_custom_alert_dialog, message,
                context.getResources().getColor(R.color.custom_alert_dialog_color_error));
    }

    /**
     * Büyük ikonlu hazır alert custom_alert_dialog pencereleri gösterir.
     * Bu alert dialoglar başlık bilgisi bulundurmadığı için başlık bilgisi null girilmiştir.
     * Ayrıca mesaj metin rengi ile ikon tint rengi aynı yapılmıştır.
     * Böylece tasarımda bütünlük yakalanmaya çalışılmıştır.
     *
     * @param iconResId            custom_alert_dialog başlığına ait icon.
     * @param message              custom_alert_dialog mesajı.
     * @param btnOkBackgroundColor ok butonuna ait arka plan rengi.
     */
    private void showPreparedDialogWithLargeIcon(final int iconResId, String message, final int btnOkBackgroundColor) {
        setDialogWithLargeIcon(iconResId, null, message);
        setOkButton(btnOkBackgroundColor);
        setIconTintColor(messageTextColor);
        show();
    }

    /**
     * Uyarı durumuna ait büyük ikonlu hazır alert custom_alert_dialog penceresi gösterir.
     * Bu alert custom_alert_dialog başlık bilgisi bulundurmadığı için başlık bilgisi null girilmiştir.
     * Ayrıca mesaj metin rengi ile ikon tint rengi aynı yapılmıştır.
     * Böylece tasarımda bütünlük yakalanmaya çalışılmıştır.
     *
     * @param message               custom_alert_dialog mesajı.
     * @param btnYesOnClickListener custom_alert_dialog penceresindeki Yes butonuna ait OnClickListener olayı.
     */
    public void showWarningDialog(String message, final OnClickListener btnYesOnClickListener) {
        setDialogWithLargeIcon(R.drawable.ic_warning_for_custom_alert_dialog, null, message);
        setPositiveButton(context.getString(R.string.yes), btnYesOnClickListener,
                context.getResources().getColor(R.color.custom_alert_dialog_color_warning), Color.WHITE);
        setNegativeButton(context.getString(R.string.no),
                new OnClickListener() {
                    @Override
                    public void onClick(CustomAlertDialog dialog) {
                        dialog.dismiss();
                    }
                },
                negativeButtonBackgroundColor, Color.WHITE);
        setIconTintColor(messageTextColor);
        show();
    }
//--------------------------------------------------------------------------------------------------

    //--------------------------------------Icon----------------------------------------------------

    /**
     * Alert custom_alert_dialog başlık ikonunu set eder.
     * Yazılımcının başlık atamadan ikon ataması durumunda başlık arka plan rengi custom_alert_dialog.xml
     * dosyasındaki atanmış renk (varsayılan başlık arka plan rengi) geçerli olucaktır.
     *
     * @param iconResId icon resources id.
     * @return this.
     */
    public CustomAlertDialog setIcon(final int iconResId) {
        ImageView imgIcon = dialog.findViewById(R.id.imgIcon);
        imgIcon.setVisibility(View.VISIBLE);
        imgIcon.setImageResource(iconResId);
        return this;
    }

    /**
     * İkonun tint rengini set eder.
     *
     * @param iconTintColor icon tint color.
     * @return this.
     */
    public CustomAlertDialog setIconTintColor(final int iconTintColor) {
        ImageView imgIcon = dialog.findViewById(R.id.imgIcon);
        imgIcon.setColorFilter(iconTintColor);
        return this;
    }
//--------------------------------------------------------------------------------------------------

    //--------------------------------------Title---------------------------------------------------
    // Başlığın arka plan rengi. Varsayılan renk koyu mor.
    private static int titleBackgroundColor = Color.parseColor("#4E308F");

    // Başlığın arka plan rengini set eder.
    public static void setTitleBackgroundColor(final int titleBackgroundColor) {
        CustomAlertDialog.titleBackgroundColor = titleBackgroundColor;

        // Tasarımda renk uyumu olması için pozitif butonun arka plan rengi de burada atanmıştır.
        // Yazılımcı isterse pozitif butonu tanımlarken butonun arka plan rengini farklı atıyabilir.
        positiveButtonBackgroundColor = titleBackgroundColor;
    }

    // Başlığın metin rengi.
    private static int titleTextColor = Color.WHITE;

    // Başlığın metin rengini set eder.
    public static void setTitleTextColor(final int titleTextColor) {
        CustomAlertDialog.titleTextColor = titleTextColor;
    }

    /**
     * Alert custom_alert_dialog başlığını set eder.
     *
     * @param title           custom_alert_dialog başlığı.
     * @param backgroundColor custom_alert_dialog başlığı arka plan rengi.
     * @param textColor       custom_alert_dialog başlığı metin rengi.
     * @return this.
     */
    public CustomAlertDialog setTitle(String title, final int backgroundColor, final int textColor) {
        // Başlığın arka plan rengi set edilir.
        LinearLayout pnlTitle = dialog.findViewById(R.id.pnlTitle);
        pnlTitle.setBackgroundColor(backgroundColor);

        if (title == null || title.isEmpty()) {
            title = context.getString(R.string.null_title_description);
        }
        TextView txtTitle = dialog.findViewById(R.id.txtTitle);
        txtTitle.setVisibility(View.VISIBLE);
        txtTitle.setText(title);
        txtTitle.setTextColor(textColor);
        return this;
    }

    /**
     * Alert custom_alert_dialog başlığını set eder.
     *
     * @param title custom_alert_dialog başlığı.
     * @return this.
     */
    public CustomAlertDialog setTitle(String title) {
        return setTitle(title, titleBackgroundColor, titleTextColor);
    }
//--------------------------------------------------------------------------------------------------

    //--------------------------------------Message-------------------------------------------------
    // Mesajın arka plan rengi. Varsayılan renk siyaha yakın bir gri.
    private static int messageBackgroundColor = Color.parseColor("#212121");

    // Mesajın arka plan rengini set eder.
    public static void setMessageBackgroundColor(final int messageBackgroundColor) {
        CustomAlertDialog.messageBackgroundColor = messageBackgroundColor;
    }

    // Mesajın metin rengi.
    private static int messageTextColor = Color.WHITE;

    // Mesajın metin rengini set eder.
    public static void setMessageTextColor(final int messageTextColor) {
        CustomAlertDialog.messageTextColor = messageTextColor;
    }

    /**
     * Alert custom_alert_dialog mesajını set eder.
     * Mesaj nesnesi her zaman gösterildiği için visibility özelliği set edilmemiştir.
     *
     * @param message         custom_alert_dialog mesajı.
     * @param backgroundColor custom_alert_dialog mesajı arka plan rengi.
     * @param textColor       custom_alert_dialog mesajı metin rengi.
     * @return this.
     */
    public CustomAlertDialog setMessage(String message, final int backgroundColor, final int textColor) {
        if (message == null || message.isEmpty()) {
            message = context.getString(R.string.null_message_description);
        }
        TextView txtMessage = dialog.findViewById(R.id.txtMessage);
        txtMessage.setText(message);
        txtMessage.setBackgroundColor(backgroundColor);
        txtMessage.setTextColor(textColor);
        return this;
    }

    /**
     * Alert custom_alert_dialog mesajını set eder.
     *
     * @param message custom_alert_dialog mesajı.
     * @return this.
     */
    public CustomAlertDialog setMessage(String message) {
        return setMessage(message, messageBackgroundColor, messageTextColor);
    }
//--------------------------------------------------------------------------------------------------

    //--------------------------PositiveButton------------------------------------------------------
    // Pozitif buton arka plan rengi.
    // Tasarım bütünlüğü açısından varsayılan rengi titleBackgroundColor yapılmıştır.
    private static int positiveButtonBackgroundColor = titleBackgroundColor;

    // Pozitif buton arka plan rengini set eder.
    public static void setPositiveButtonBackgroundColor(final int positiveButtonBackgroundColor) {
        CustomAlertDialog.positiveButtonBackgroundColor = positiveButtonBackgroundColor;
    }

    // Pozitif butona ait textColor rengi.
    private static int positiveButtonTextColor = Color.WHITE;

    // Pozitif butona ait textColor rengini set eder.
    public static void setPositiveButtonTextColor(final int positiveButtonTextColor) {
        CustomAlertDialog.positiveButtonTextColor = positiveButtonTextColor;
    }

    /**
     * Alert custom_alert_dialog penceresinin pozitif butonunu set eder.
     *
     * @param btnTitle           pozitif buton başlığı.
     * @param listener           pozitif butona ait OnClickListener olayı.
     * @param btnBackgroundColor pozitif butonun arka plan rengi.
     * @param textColor          pozitif butonun metin rengi.
     * @return this
     */
    public CustomAlertDialog setPositiveButton(String btnTitle, final OnClickListener listener,
                                               final int btnBackgroundColor, final int textColor) {
        txtYes = dialog.findViewById(R.id.txtYes);
        txtYes.setVisibility(View.VISIBLE);
        txtYes.setBackgroundColor(btnBackgroundColor);
        if (listener != null) {
            txtYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(CustomAlertDialog.this);
                }
            });
        }
        if (btnTitle == null || btnTitle.isEmpty()) {
            btnTitle = context.getString(R.string.yes);
        }
        txtYes.setText(btnTitle);
        txtYes.setTextColor(textColor);
        takeHeightsOfButtons();
        return this;
    }

    /**
     * Alert custom_alert_dialog penceresinin pozitif butonunu set eder.
     *
     * @param btnTitle pozitif buton başlığı.
     * @param listener pozitif butona ait OnClickListener olayı.
     * @return this.
     */
    public CustomAlertDialog setPositiveButton(String btnTitle, final OnClickListener listener) {
        return setPositiveButton(btnTitle, listener, positiveButtonBackgroundColor, positiveButtonTextColor);
    }

    /**
     * Pozitif butonun sol tarafına ikon set eder.
     * setPositiveButton() fonksiyonu kullanılmadan bu fonksiyon kullanılırsa ikon görünmeyecektir.
     * Butonun ikonu olması için öncelikle kendisinin olması gerekmektedir.
     *
     * @param icon pozitif buton ikonu.
     * @return this.
     */
    public CustomAlertDialog setPositiveButtonIcon(final int icon) {
        TextView txtYes = dialog.findViewById(R.id.txtYes);
        txtYes.setCompoundDrawablesWithIntrinsicBounds(icon, 0, 0, 0);
        txtYes.setCompoundDrawablePadding(5);
        return this;
    }
//--------------------------------------------------------------------------------------------------

    //--------------------------NegativeButton------------------------------------------------------
    // Negatif buton arka plan rengi. Varsayılan renk koyu gri.
    private static int negativeButtonBackgroundColor = Color.parseColor("#434343");

    // Negatif buton arka plan rengini set eder.
    public static void setNegativeButtonBackgroundColor(final int negativeButtonBackgroundColor) {
        CustomAlertDialog.negativeButtonBackgroundColor = negativeButtonBackgroundColor;
    }

    // Negatif butona ait textColor rengi.
    private static int negativeButtonTextColor = Color.WHITE;

    // Negatif butona ait textColor rengini set eder.
    public static void setNegativeButtonTextColor(final int negativeButtonTextColor) {
        CustomAlertDialog.negativeButtonTextColor = negativeButtonTextColor;
    }

    /**
     * Alert custom_alert_dialog penceresinin negatif butonunu set eder.
     *
     * @param btnTitle           negatif buton başlığı.
     * @param listener           negatif butona ait OnClickListener olayı.
     * @param btnBackgroundColor negatif butonun arka plan rengi.
     * @param textColor          negatif butonun metin rengi.
     * @return this
     */
    public CustomAlertDialog setNegativeButton(String btnTitle, final OnClickListener listener,
                                               final int btnBackgroundColor, final int textColor) {
        txtNo = dialog.findViewById(R.id.txtNo);
        txtNo.setVisibility(View.VISIBLE);
        txtNo.setBackgroundColor(btnBackgroundColor);
        if (listener != null) {
            txtNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(CustomAlertDialog.this);
                }
            });
        }
        if (btnTitle == null || btnTitle.isEmpty()) {
            btnTitle = context.getString(R.string.no);
        }
        txtNo.setText(btnTitle);
        txtNo.setTextColor(textColor);
        takeHeightsOfButtons();
        return this;
    }

    /**
     * Alert custom_alert_dialog penceresinin negatif butonunu set eder.
     *
     * @param btnTitle negatif buton başlığı.
     * @param listener negatif butona ait OnClickListener olayı.
     * @return this
     */
    public CustomAlertDialog setNegativeButton(String btnTitle, final OnClickListener listener) {
        return setNegativeButton(btnTitle, listener, negativeButtonBackgroundColor, negativeButtonTextColor);
    }

    /**
     * Negatif butonun sol tarafına ikon set eder.
     * setNegativeButton() fonksiyonu kullanılmadan bu fonksiyon kullanılırsa ikon görünmeyecektir.
     * Butonun ikonu olması için öncelikle kendisinin olması gerekmektedir.
     *
     * @param icon negatif buton ikonu.
     * @return this.
     */
    public CustomAlertDialog setNegativeButtonIcon(final int icon) {
        TextView txtNo = dialog.findViewById(R.id.txtNo);
        txtNo.setCompoundDrawablesWithIntrinsicBounds(icon, 0, 0, 0);
        txtNo.setCompoundDrawablePadding(5);
        return this;
    }
//--------------------------------------------------------------------------------------------------

    private int positiveButtonHeight, negativeButtonHeight;

    /**
     * İki buton da tanımlanmışsa butonların yükseklik bilgisini alır.
     * <p>
     * Pozitif ve negatif butonların yükseklikleri bazı durumlarda farklı olabilmektedir.
     * Bu durumda yüksekliği düşük olan buton pnlButtons içinde daha az yer kapladığı için
     * arka plan rengi eksik görünüyordu. Eksik olan yerler custom_alert_dialog.xml'den gelen background_color
     * olarak görünüyordu. Bu da renk uyumsuzluğu oluşturuyordu. Bu sorunu çözmek için butonların
     * yükseklikleri karşılaştırılmıştır. Yüksekliği düşük olan butonun arka plan rengi
     * pnlButtons'ın arka plan rengi olarak ayarlanmıştır.
     * Böylece eksik renk sorunu ortadan kalkmıştır.
     */
    private void takeHeightsOfButtons() {
        // İki buton da tanımlanmadığı sürece butonların yükseklik bilgisini almaya gerek yoktur.
        if (txtYes != null && txtNo != null) {
            txtYes.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    txtYes.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    positiveButtonHeight = txtYes.getHeight();
                    setPnlButtonsBackgroundColor();
                }
            });
            txtNo.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    txtNo.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    negativeButtonHeight = txtNo.getHeight();
                    setPnlButtonsBackgroundColor();
                }
            });
        }
    }

    /**
     * pnlButtons arka plan rengini yüksekliği düşük olan butonun arka plan rengine göre set eder.
     * TextView'lerin getBackgroundColor() fonksiyonu olmadığı için
     * textView'lerin arka plan renkleri aşağıdaki yöntemle alınmıştır.
     */
    private void setPnlButtonsBackgroundColor() {
        // Bu kontrol yapılarak gereksiz işlem yapılması önlenmiştir.
        if (positiveButtonHeight != 0 && negativeButtonHeight != 0 && positiveButtonHeight != negativeButtonHeight) {
            Log.i(CustomAlertDialog.class.getSimpleName(), "setPnlButtonsBackgroundColor: The heights of positive and negative buttons is different!\n" +
                    "The heights of buttons (positive - negative) : " + positiveButtonHeight + " - " + negativeButtonHeight);
            LinearLayout pnlButtons = dialog.findViewById(R.id.pnlButtons);
            int backgroundColor;
            if (positiveButtonHeight > negativeButtonHeight) {
                backgroundColor = negativeButtonBackgroundColor;
                if (txtNo.getBackground() instanceof ColorDrawable) {
                    ColorDrawable cd = (ColorDrawable) txtNo.getBackground();
                    backgroundColor = cd.getColor();
                }
            } else {
                backgroundColor = positiveButtonBackgroundColor;
                if (txtYes.getBackground() instanceof ColorDrawable) {
                    ColorDrawable cd = (ColorDrawable) txtYes.getBackground();
                    backgroundColor = cd.getColor();
                }
            }
            pnlButtons.setBackgroundColor(backgroundColor);
        }
    }
//--------------------------------------------------------------------------------------------------

    /**
     * Başlıksız, sadece mesaj gösteren ve tamam butonu barındıran bir alert custom_alert_dialog penceresi açar.
     * Tamam butonunun görevi alert custom_alert_dialog penceresinden çıkış yapmayı sağlamaktır.
     * <p>
     * Bu fonksiyonda Tamam butonu özelleştirilmiştir.
     * Tamam butonunun zeminden biraz daha uzak mesaj alanına da biraz daha yakın olması için
     * butonun padding değerleri sıfırlanmış ve sadece paddingBottom değeri verilmiştir.
     * Tamam butonunun arka plan rengi mesaj alanıyla bütünleşmesi için messageBackgroundColor
     * olarak belirlenmiştir. Böylece daha sade bir tasarım elde edilmiştir.
     *
     * @param message                 custom_alert_dialog mesajı.
     * @param positiveButtonTextColor pozitif butonun textColor rengi.
     */
    public void showDialogWithoutTitle(String message, final int positiveButtonTextColor) {
        setMessage(message);
        setPositiveButton(context.getString(R.string.ok), new OnClickListener() {
            @Override
            public void onClick(CustomAlertDialog dialog) {
                dialog.dismiss();
            }
        }, messageBackgroundColor, positiveButtonTextColor);
        txtYes.setPadding(0, 0, 0, 40);

        TextView txtNo = dialog.findViewById(R.id.txtNo);
        txtNo.setVisibility(View.VISIBLE);
        txtNo.setBackgroundColor(messageBackgroundColor);
        txtNo.setText(null);
        // txtYes butonunun padding değerleri yukarıda değiştiği ve iki butonun yüksekliklerinin de
        // aynı olması gerektiği için yukarıdaki padding değerlerinin aynısı txtNo için de verilmiştir.
        txtNo.setPadding(0, 0, 0, 40);
        show();
    }

    /**
     * Başlıksız, sadece mesaj gösteren ve tamam butonu barındıran bir alert custom_alert_dialog penceresi açar.
     * Pozitif butonun textColor rengi titleTextColor olarak belirlenmiştir.
     *
     * @param message custom_alert_dialog mesajı.
     */
    public void showDialogWithoutTitle(String message) {
        showDialogWithoutTitle(message, titleTextColor);
    }

    /**
     * Başlık, mesaj ve tamam butonu barındıran ikonsuz bir alert custom_alert_dialog penceresi açar.
     *
     * @param title   custom_alert_dialog başlığı.
     * @param message custom_alert_dialog mesajı.
     */
    public void showDialogWithoutIcon(String title, String message) {
        setTitle(title);
        setMessage(message);
        setPositiveButton(context.getString(R.string.ok), new OnClickListener() {
            @Override
            public void onClick(CustomAlertDialog dialog) {
                dialog.dismiss();
            }
        });
        show();
    }

    /**
     * Uygulama izinleriyle ilgili kullanıcıya bilgi vermek için bir alert custom_alert_dialog penceresi açar.
     * Başlık bilgisinin null atanmasına izin verilir. Null durumunda başlık alanı görünmeyecektir.
     *
     * @param iconResId                     izinle ilgili bir ikon.
     * @param title                         izinle ilgili bir başlık (null olabilir).
     * @param message                       izni isteme nedenini açıklayan bir mesaj.
     * @param positiveButtonOnClickListener pozitif butona ait OnClickListener olayı.
     */
    public void showPermissionDialog(final int iconResId, final String title, String message,
                                     final OnClickListener positiveButtonOnClickListener) {
        setDialogWithLargeIcon(iconResId, title, message);
        setPositiveButton(context.getString(R.string.go_on), positiveButtonOnClickListener);
        setNegativeButton(context.getString(R.string.cancel), new OnClickListener() {
            @Override
            public void onClick(CustomAlertDialog dialog) {
                dialog.dismiss();
            }
        });
        show();
    }

    /**
     * Büyük ikonlu bir alert custom_alert_dialog penceresi gösterir.
     * Farklı bir tasarımda alert custom_alert_dialog penceresi görüntülemek için bu fonksiyon yazılmıştır.
     * Bu tasarımda başlık arka plan rengi ile mesaj arka plan rengi aynıdır.
     * Böylece tasarım olarak pencerede bir bütünlük sağlanmıştır.
     * Başlık bilgisinin null atanmasına izin verilir. Null durumunda başlık alanı görünmeyecektir.
     *
     * @param iconResId custom_alert_dialog ikonu.
     * @param title     custom_alert_dialog başlığı (null olabilir).
     * @param message   custom_alert_dialog mesajı.
     * @return this.
     */
    public CustomAlertDialog setDialogWithLargeIcon(final int iconResId, final String title, String message) {
        // Başlık kutusu set edilir.
        LinearLayout pnlTitle = dialog.findViewById(R.id.pnlTitle);
        pnlTitle.setOrientation(LinearLayout.VERTICAL);
        pnlTitle.setGravity(Gravity.CENTER_HORIZONTAL);
        pnlTitle.setBackgroundColor(messageBackgroundColor);
        pnlTitle.setPadding(0, 40, 0, 0);

        // İkon set edilir.
        LinearLayout.LayoutParams imgIconLayoutParams = new LinearLayout.LayoutParams(200, 200);
        ImageView imgIcon = dialog.findViewById(R.id.imgIcon);
        imgIcon.setLayoutParams(imgIconLayoutParams);
        imgIcon.setVisibility(View.VISIBLE);
        imgIcon.setImageResource(iconResId);

        // Başlık bilgisi set edilir. Başlık bilgisi null geldiyse başlık alanı görünmeyecektir.
        if (title != null) {
            LinearLayout.LayoutParams txtTitleLayoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            // İkonla arasına margin verilir.
            txtTitleLayoutParams.setMargins(0, 40, 0, 0);
            TextView txtTitle = dialog.findViewById(R.id.txtTitle);
            txtTitle.setLayoutParams(txtTitleLayoutParams);
            txtTitle.setVisibility(View.VISIBLE);
            txtTitle.setText(title);
            txtTitle.setTextColor(messageTextColor);
            txtTitle.setGravity(Gravity.CENTER_HORIZONTAL);
            // Başlığın yatayda ortalanması gerekmektedir.
            // Fakat custom_alert_dialog.xml dosyasında txtTitle'ın 10dp'lik paddingStart'ı olduğu için tam ortalanmıyordu.
            // Bu durumu düzeltmek için yeni padding değerleri verilmiştir.
            txtTitle.setPadding(50, 0, 50, 0);
        }

        setMessage(message);
        return this;
    }

    //---------------------------Methods of Items---------------------------------------------------

    /**
     * Dışarıdan aldığı CharSequence dizisini barındıran bir alert custom_alert_dialog penceresi gösterir.
     * Bu fonksiyonda mesaj alanı gösterilmez. setMessage() fonksiyonu setItems() fonksiyonundan
     * önce veya sonra tanımlansa bile mesaj alanı görünmeyecektir.
     *
     * @param items               liste halinde gösterilmek istenen CharSequence dizisi.
     * @param listener            liste üzerindeki herhangi bir elemana tıklanma olayı.
     * @param itemBackgroundColor listedeki elemanların arka plan rengi.
     * @return this.
     */
    public CustomAlertDialog setItems(final CharSequence[] items, final OnItemClickListener listener,
                                      final int itemBackgroundColor) {
        LinearLayout itemsContainer = getItemsContainer();
        for (int i = 0; i < items.length; i++) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins(0, 0, 0, 10);
            TextView txtItem = new TextView(context);
            txtItem.setLayoutParams(layoutParams);
            txtItem.setPadding(30, 15, 30, 15);
            txtItem.setText(items[i]);
            txtItem.setTextColor(messageTextColor);
            txtItem.setTextSize(18);
            final int iFinal = i;
            txtItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(CustomAlertDialog.this, iFinal);
                }
            });
            itemsContainer.addView(txtItem);
        }
        // Yukarıdaki döngüde her seferinde itemBackgroundColor != 0 kontrolü yapılmasın diye
        // kontrolü burda yaptık.
        if (itemBackgroundColor != 0) {
            for (int i = 0; i < items.length; i++) {
                itemsContainer.getChildAt(i).setBackgroundColor(itemBackgroundColor);
            }
        }
        addItemsContainerToPnlDialog(itemsContainer);
        return this;
    }

    /**
     * Dışarıdan aldığı CharSequence dizisini barındıran bir alert custom_alert_dialog penceresi gösterir.
     *
     * @param items    liste halinde gösterilmek istenen CharSequence dizisi.
     * @param listener liste üzerindeki herhangi bir elemana tıklanma olayı.
     * @return this.
     */
    public CustomAlertDialog setItems(final CharSequence[] items, final OnItemClickListener listener) {
        return setItems(items, listener, 0);
    }

    /**
     * Dışarıdan aldığı CharSequence dizisi üzerinde tek bir elemanın seçilebilmesini sağlayan
     * bir alert custom_alert_dialog penceresi gösterir.
     * Bu fonksiyonda mesaj alanı gösterilmez. setMessage() fonksiyonu setSingleChoiceItems()
     * fonksiyonundan önce veya sonra tanımlansa bile mesaj alanı görünmeyecektir.
     *
     * @param items       liste halinde gösterilmek istenen CharSequence dizisi.
     * @param checkedItem listede başlangıçta seçili olarak gelecek olan elemanın index numarası.
     * @param listener    liste üzerindeki herhangi bir elemana tıklanma olayı.
     * @return this.
     */
    public CustomAlertDialog setSingleChoiceItems(final CharSequence[] items, final int checkedItem,
                                                  final OnItemClickListener listener) {
        LinearLayout.LayoutParams radioGroupLayoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        RadioGroup radioGroup = new RadioGroup(context);
        radioGroup.setLayoutParams(radioGroupLayoutParams);

        for (int i = 0; i < items.length; i++) {
            RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins(0, 0, 0, 10);
            RadioButton rdoBtnItem = new RadioButton(context);
            rdoBtnItem.setLayoutParams(layoutParams);
            rdoBtnItem.setId(i);
            rdoBtnItem.setPadding(30, 15, 30, 15);
            rdoBtnItem.setText(items[i]);
            rdoBtnItem.setTextColor(messageTextColor);
            rdoBtnItem.setTextSize(18);
            final int iFinal = i;
            rdoBtnItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(CustomAlertDialog.this, iFinal);
                }
            });
            radioGroup.addView(rdoBtnItem);
        }

        if (checkedItem > -1 && checkedItem < items.length) {
            radioGroup.check(checkedItem);
            radioGroup.setVerticalScrollbarPosition(checkedItem);
        } else {
            radioGroup.check(0);
        }
        LinearLayout itemsContainer = getItemsContainer();
        itemsContainer.addView(radioGroup);
        addItemsContainerToPnlDialog(itemsContainer);
        return this;
    }

    /**
     * Dışarıdan aldığı CharSequence dizisi üzerinde çoklu seçim işlemi yapılmasına olanak sağlayan
     * bir alert custom_alert_dialog penceresi gösterir.
     * Bu fonksiyonda mesaj alanı gösterilmez. setMessage() fonksiyonu setMultiChoiceItems()
     * fonksiyonundan önce veya sonra tanımlansa bile mesaj alanı görünmeyecektir.
     *
     * @param items        liste halinde gösterilmek istenen CharSequence dizisi.
     * @param checkedItems listedeki elemanların seçili olma durumlarını tutan boolean dizisi.
     * @param listener     liste üzerindeki herhangi bir elemana tıklanma olayı.
     * @return this.
     */
    public CustomAlertDialog setMultiChoiceItems(final CharSequence[] items, final boolean[] checkedItems,
                                                 final OnMultiChoiceClickListener listener) {
        LinearLayout itemsContainer = getItemsContainer();
        for (int i = 0; i < items.length; i++) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins(0, 0, 0, 10);
            CheckBox chkItem = new CheckBox(context);
            chkItem.setLayoutParams(layoutParams);
            chkItem.setPadding(30, 15, 30, 15);
            chkItem.setChecked(checkedItems[i]);
            chkItem.setText(items[i]);
            chkItem.setTextColor(messageTextColor);
            chkItem.setTextSize(18);
            final int iFinal = i;
            chkItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    listener.onClick(CustomAlertDialog.this, iFinal, isChecked);
                }
            });
            itemsContainer.addView(chkItem);
        }
        addItemsContainerToPnlDialog(itemsContainer);
        return this;
    }

    /**
     * Liste halinde gösterilecek elemanları barındıran LinearLayout'u döndürür.
     *
     * @return liste konteynır layoutu.
     */
    private LinearLayout getItemsContainer() {
        // Mesaj yerine liste gösterileceği için mesajın gösterilmesi kapatılır.
        TextView txtMessage = dialog.findViewById(R.id.txtMessage);
        txtMessage.setVisibility(View.GONE);

        // Elemanların gösterileceği konteynır tanımlanır.
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        LinearLayout itemsContainer = new LinearLayout(context);
        itemsContainer.setLayoutParams(layoutParams);
        itemsContainer.setOrientation(LinearLayout.VERTICAL);
        itemsContainer.setBackgroundColor(messageBackgroundColor);
        // Item'ın marginBottom = 10 olduğu için buradaki paddingBottom değeri 10 eksik verildi.
        itemsContainer.setPadding(30, 30, 30, 20);
        return itemsContainer;
    }

    /**
     * Mesaj alanına items container layotunu ekler.
     *
     * @param itemsContainer items container layotu.
     */
    private void addItemsContainerToPnlDialog(LinearLayout itemsContainer) {
        LinearLayout pnlDialog = dialog.findViewById(R.id.pnlDialog);
        // pnlDialog layoutunun son elemanı pnlButtons layoutu olduğu için
        // pnlItemsContainer layoutu sondan bir önceki yere eklenmiştir.
        pnlDialog.addView(itemsContainer, pnlDialog.getChildCount() - 1);
    }
//--------------------------------------------------------------------------------------------------

    private boolean cancelable = true;

    // cancelable değişkenini set eder.
    public CustomAlertDialog setCancelable(final boolean cancelable) {
        this.cancelable = cancelable;
        return this;
    }

    // Dialog penceresinin cancelable özelliği için OnClickListener fonksiyonunu set eder.
    private void setDialogWindowForCancelable() {
        if (cancelable) {
            LinearLayout pnlDialogWindow = dialog.findViewById(R.id.pnlDialogWindow);
            pnlDialogWindow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
        }
    }

    // Dialog penceresini açar.
    public void show() {
        setDialogWindowForCancelable();
        dialog.show();
    }

    // Dialog penceresini kapatır.
    public void dismiss() {
        dialog.dismiss();
    }


    /**
     * Pozitif ve negatif butonların tıklanma olayını ele alır.
     */
    public interface OnClickListener {
        /**
         * @param dialog this custom_alert_dialog.
         */
        void onClick(CustomAlertDialog dialog);
    }

    /**
     * setItems() ve setSingleChoiceItems() ile oluşturulmuş elemanların tıklanma olayını ele alır.
     */
    public interface OnItemClickListener {
        /**
         * @param dialog this custom_alert_dialog.
         * @param which  tıklanan elemanın (textView, radioButton) items dizisindeki index bilgisi.
         */
        void onClick(CustomAlertDialog dialog, int which);
    }

    /**
     * setMultiChoiceItems() ile oluşturulmuş elemanların tıklanma olayını ele alır.
     */
    public interface OnMultiChoiceClickListener {
        /**
         * @param dialog    this custom_alert_dialog.
         * @param which     tıklanan elemanın (checkBox) items dizisindeki index bilgisi.
         * @param isChecked tıklanan elemanın (checkBox) yeni seçim durumu.
         */
        void onClick(CustomAlertDialog dialog, int which, boolean isChecked);
    }

}
