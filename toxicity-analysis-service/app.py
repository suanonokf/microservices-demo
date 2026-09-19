import os
from dotenv import load_dotenv
import py_eureka_client.eureka_client as eureka_client

from transformers import pipeline
from flask import request,jsonify,Flask
app = Flask(__name__)


load_dotenv()
# Eureka registration
eureka_client.init(
    eureka_server=os.getenv("EUREKA_URL"),
    app_name="toxicity-analysis-service",
    instance_port=5000
)

print("Loading models...")
access_token = os.getenv("ACCESS_TOKEN ")
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
