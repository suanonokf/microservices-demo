from transformers import pipeline
from flask import request,jsonify,Flask
app = Flask(__name__)

print("Loading models...")

access_token =''
toxicity_classifier = pipeline('text-classification',model='unitary/toxic-bert',token=access_token)
print("Model loaded")

@app.route('/toxicity-test',methods=['POST'])
def toxicity_test():
    try:
        data = request.get_json()
        text = data.get('text', '')
        if not text:
            return jsonify({'error': 'No text provided'})
        toxicity = toxicity_classifier(text)
        toxicity_score = toxicity[0]['score']*100
        label = toxicity[0]['label']
        return jsonify({'label': label,
                        'toxicityScore': toxicity_score,
                        'rawResults': toxicity})
    except Exception as e:
        return jsonify({'error': str(e)})

app.run(host='0.0.0.0', port=5000)