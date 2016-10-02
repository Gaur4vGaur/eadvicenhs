import play.api.libs.json.Json

case class Assessment(symptoms: List[String], investigation: List[String], referral: String)

object Assessment {implicit val formats = Json.format[Assessment]}



val hs = List("Unexplained weight loss.",
  "Change in bowel habit (both frequency of defecation and consistency of stool) must be recognised.",
  "Tenesmus. May be a feature (for example, with fissures).",
  "Anal symptoms - eg, soreness or pain may occur with fissures, itching with piles.",
  "Family history of bowel cancer or polyposis.",
  "Past medical history. Careful documentation with particular reference to causes of bleeding and GI tract pathology. Any history of trauma should not be overlooked.",
  "Medication history. This may identify causes of bleeding (for example, warfarin and aspirin).")


val iv = List("FBC (and group and save if bleeding is profound or anaemia suspected).",
  "Ferritin and iron studies if iron-deficiency anaemia is suspected.",
  "Clotting studies may be appropriate.",
  "LFTs may be indicated if liver disease is suspected.",
  "Faecal calprotectin is a useful screen in younger patients suspected of having inflammatory bowel disease, and has a high positive predictive value.")

val ref = "Routine - may be appropriate for low-risk and benign conditions. Urgent (within two weeks).Emergency (immediate) when there is massive bleeding."

val as = Assessment(hs, iv, ref)

Json.toJson(as)


val time = System.currentTimeMillis

import java.util.Date

val date = new Date(time)