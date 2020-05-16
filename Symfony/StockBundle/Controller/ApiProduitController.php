<?php

namespace StockBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use StockBundle\Entity\produit;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Security\Core\Exception\AccessDeniedException;



class ApiProduitController extends Controller
{
  public function allAction()
  {
    $produits = $this->getDoctrine()->getManager()->getRepository('StockBundle:Produit')->findAll();
    /*dump($produits);
    exit();*/
       $normalizer = new ObjectNormalizer();
       $normalizer->setCircularReferenceLimit(2);
       $encoder = new JsonEncoder();
       $normalizer->setCircularReferenceHandler(function ($object) {
           return $object->getId();
       });
       $serializer = new Serializer(array($normalizer), array($encoder));
       $formatted = $serializer->normalize($produits);
       return new JsonResponse($formatted);
   /* $serializer = new Serializer([new ObjectNormalizer()]);
    $formatted = $serializer->normalize($produits);
    return new JsonResponse($formatted);*/
  }
    public function allCAction()
    {
        $categories = $this->getDoctrine()->getManager()->getRepository('StockBundle:Categorie')->findAll();

        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceLimit(2);
        $encoder = new JsonEncoder();
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object->getId();
        });
        $serializer = new Serializer(array($normalizer), array($encoder));
        $formatted = $serializer->normalize($categories);
        return new JsonResponse($formatted);
       
    }
    public function rechAction($nom)
    {
        $categories = $this->getDoctrine()->getManager()->getRepository('StockBundle:Produit')->findName($nom);
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceLimit(2);
        $encoder = new JsonEncoder();
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object->getId();
        });
        $serializer = new Serializer(array($normalizer), array($encoder));
        $formatted = $serializer->normalize($categories);
        return new JsonResponse($formatted);
    }
    public function FindAction($id)
    {
        $produits = $this->getDoctrine()->getManager()->getRepository('StockBundle:Produit')->find($id);
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceLimit(2);
        $encoder = new JsonEncoder();
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object->getId();
        });
        $serializer = new Serializer(array($normalizer), array($encoder));
        $formatted = $serializer->normalize($produits);
        return new JsonResponse($formatted);
    }

    public function FindCAction($id)
    {
        $produits = $this->getDoctrine()->getManager()->getRepository('StockBundle:Produit')->findProduit($id);
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceLimit(2);
        $encoder = new JsonEncoder();
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object->getId();
        });
        $serializer = new Serializer(array($normalizer), array($encoder));
        $formatted = $serializer->normalize($produits);
        return new JsonResponse($formatted);
    }

    public function newAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $produit = new produit();
        $produit ->setName($request->get('nom'));
        $produit ->setQte($request->get('qte'));
        $produit ->setEtat($request->get('etat'));
        $produit ->setCat($request->get('cat'));
        $produit ->setPrix($request->get('prix'));
        $produit ->setSize($request->get('size'));
        $produit ->setUrl($request->get('url'));
        $em->persist($produit);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($produit);
        return new JsonResponse($formatted);
    }
}
