<?php

namespace StockBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $produits = $em->getRepository('StockBundle:Produit')->findAll();

        return $this->render('@Stock/Produit/index.html.twig', array(
            'produits' => $produits,
        ));
    }

}
