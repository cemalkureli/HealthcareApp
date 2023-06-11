package com.example.healthcare.Database

class PackagesAndDetails {
    val packages = arrayOf(
        arrayOf("Package 1: Full Body Checkup", "", "", "", "500"),
        arrayOf("Package 2: Blood Glucose Fasting", "", "", "", "400"),
        arrayOf("Package 3: COVID-19 Antibody - IgG", "", "", "", "100"),
        arrayOf("Package 4: Thyroid Check", "", "", "", "300"),
        arrayOf("Package 5: Immunity Check", "", "", "", "300"),
        arrayOf("Package 6: Cardiovascular Profile", "", "", "", "300"),
        arrayOf("Package 7: Diabetes Screening", "", "", "", "200"),
        arrayOf("Package 8: Allergy Testing", "", "", "", "250"),
        arrayOf("Package 9: Cancer Screening", "", "", "", "350"),
        arrayOf("Package 10: Digestive Health Panel", "", "", "", "300"),
        arrayOf("Package 11: Hormone Panel", "", "", "", "400"),
        arrayOf("Package 12: Renal Function Panel", "", "", "", "300"),
        arrayOf("Package 13: Liver Function Panel", "", "", "", "400"),
        arrayOf("Package 14: Nutritional Assessment", "", "", "", "200"),
        arrayOf("Package 15: Bone Health Panel", "", "", "", "500"),
        arrayOf("Package 16: Genetic Testing", "", "", "", "1000"),
        arrayOf("Package 17: Thyroid Function Panel", "", "", "", "600"),
        arrayOf("Package 18: Respiratory Profile", "", "", "", "300"),
        arrayOf("Package 19: Metabolic Syndrome Evaluation", "", "", "", "400"),
        arrayOf("Package 20: Women's Health Panel", "", "", "", "500")
    )


    val packageDetails = arrayOf(
        "Full Body Checkup\n" +
                "Complete Hemogram\n" +
                "HbA1c\n" +
                "Iron Studies\n" +
                "Kidney Function Test\n" +
                "LDH Lactate Dehydrogenase, Serum\n" +
                "Lipid Profile\n" +
                "Liver Function Test",

        "Blood Glucose Fasting",

        "COVID-19 Antibody - IgG",

        "Thyroid Profile-Total (T3, T4 & TSH Ultra-sensitive)\n" +
                "Complete Hemogram\n" +
                "CRP (C Reactive Protein) Quantitative, Serum\n" +
                "Iron Studies\n" +
                "Kidney Function Test\n" +
                "Vitamin D Total-25 Hydroxy\n" +
                "Liver Function Test\n" +
                "Lipid Profile",

        "Immunity Check",

        "Cardiovascular Profile\n" +
                "Lipid Profile\n" +
                "Homocysteine, Plasma\n" +
                "Hs-CRP\n" +
                "Lp(a)\n" +
                "Apolipoprotein A1\n" +
                "Apolipoprotein B\n" +
                "Natriuretic Peptide B (BNP)",

        "Diabetes Screening\n" +
                "Blood Glucose Fasting\n" +
                "HbA1c\n" +
                "Insulin, Fasting\n" +
                "C-Peptide, Fasting\n" +
                "Microalbumin, Random Urine\n" +
                "Kidney Function Test\n" +
                "Liver Function Test\n" +
                "Lipid Profile",

        "Allergy Testing\n" +
                "Total IgE\n" +
                "Specific IgE - Food Panel\n" +
                "Specific IgE - Inhalant Panel\n" +
                "Specific IgE - Drug Panel",

        "Cancer Screening\n" +
                "Complete Blood Count (CBC)\n" +
                "PSA (Prostate-Specific Antigen), Total\n" +
                "CA 125 (Cancer Antigen 125)\n" +
                "CA 19-9 (Cancer Antigen 19-9)\n" +
                "CEA (Carcinoembryonic Antigen)",

        "Digestive Health Panel\n" +
                "Stool Routine\n" +
                "Stool Culture\n" +
                "Liver Function Test\n" +
                "Amylase, Serum\n" +
                "Lipase, Serum\n" +
                "Anti-Helicobacter Pylori IgG",

        "Hormone Panel\n" +
                "Thyroid Profile-Total (T3, T4 & TSH Ultra-sensitive)\n" +
                "Luteinizing Hormone (LH)\n" +
                "Follicle Stimulating Hormone (FSH)\n" +
                "Estradiol\n" +
                "Progesterone\n" +
                "Prolactin\n" +
                "Testosterone, Total\n" +
                "Sex Hormone Binding Globulin (SHBG)",

        "Renal Function Panel\n" +
                "Creatinine, Serum\n" +
                "Urea, Serum\n" +
                "Calcium, Serum\n" +
                "Phosphorus, Serum\n" +
                "Uric Acid, Serum\n" +
                "Electrolyte Panel (Sodium, Potassium, Chloride)",

        "Liver Function Panel\n" +
                "Bilirubin, Total\n" +
                "Bilirubin, Direct\n" +
                "Bilirubin, Indirect\n" +
                "SGOT (AST)\n" +
                "SGPT (ALT)\n" +
                "Alkaline Phosphatase\n" +
                "Total Protein, Serum\n" +
                "Albumin, Serum\n" +
                "Globulin, Serum",

        "Nutritional Assessment\n" +
                "Vitamin D Total-25 Hydroxy\n" +
                "Vitamin B12\n" +
                "Folic Acid (Folate), Serum\n" +
                "Iron Studies\n" +
                "Calcium, Serum\n" +
                "Phosphorus, Serum\n" +
                "Magnesium, Serum\n" +
                "Total Protein, Serum\n" +
                "Albumin, Serum\n" +
                "Globulin, Serum",

        "Bone Health Panel\n" +
                "Vitamin D Total-25 Hydroxy\n" +
                "Calcium, Serum\n" +
                "Phosphorus, Serum\n" +
                "Magnesium, Serum\n" +
                "Alkaline Phosphatase\n" +
                "Parathyroid Hormone (PTH)\n" +
                "Vitamin B12\n" +
                "Folic Acid (Folate), Serum",

        "Genetic Testing\n" +
                "Genetic Testing Panel 1\n" +
                "Genetic Testing Panel 2\n" +
                "Genetic Testing Panel 3\n" +
                "Genetic Testing Panel 4",

        "Thyroid Function Panel\n" +
                "Thyroid Profile-Total (T3, T4 & TSH Ultra-sensitive)\n" +
                "Free T3\n" +
                "Free T4\n" +
                "T3 Uptake\n" +
                "Thyroid Peroxidase Antibodies (TPO)\n" +
                "Thyroglobulin Antibodies (Tg)\n" +
                "Thyroid Stimulating Immunoglobulin (TSI)\n" +
                "Reverse T3 (rT3)",

        "Respiratory Profile\n" +
                "Pulmonary Function Test (PFT)\n" +
                "Chest X-ray\n" +
                "Complete Blood Count (CBC)\n" +
                "Eosinophil Count, Absolute\n" +
                "Total IgE\n" +
                "Specific IgE - Inhalant Panel\n" +
                "Lung Function Test\n" +
                "Arterial Blood Gas (ABG)",

        "Metabolic Syndrome Evaluation\n" +
                "Fasting Blood Sugar (FBS)\n" +
                "HbA1c\n" +
                "Lipid Profile\n" +
                "Liver Function Test\n" +
                "Kidney Function Test\n" +
                "Urine Routine\n" +
                "Microalbumin, Random Urine\n" +
                "Uric Acid, Serum\n" +
                "Calcium, Serum\n" +
                "Phosphorus, Serum\n" +
                "Magnesium, Serum\n" +
                "C-Reactive Protein (CRP)",

        "Women's Health Panel\n" +
                "Complete Blood Count (CBC)\n" +
                "Thyroid Profile-Total (T3, T4 & TSH Ultra-sensitive)\n" +
                "Pap Smear\n" +
                "Breast Cancer Gene (BRCA) Testing\n" +
                "Bone Mineral Density (BMD) Test\n" +
                "Hormone Panel\n" +
                "Vitamin D Total-25 Hydroxy"
    )
}